var manualBrewing = angular.module('breweryApp');

manualBrewing.controller('ManualBrewingCtrl', function ($scope, $rootScope, restService, refreshService) {
    'use strict';
    var ingredientsResourceUrl = $rootScope.resourcePath + 'brewer/ingredients';
    var kettleResourceUrl = $rootScope.resourcePath + 'brewer/kettle';
    
    $scope.operations = {
        flush: {
            activate: function () {
                restService.del(ingredientsResourceUrl);
                refreshService.refreshReadings(kettleResourceUrl);
            }
        },
        ingredient: {
            activate: function (type, newValue) {
                if (newValue !== 0) {
                    var ingredient = restService.createIngredient(type, newValue, 'liter');
                    restService.postWithData(ingredientsResourceUrl, ingredient);
                }
            }
        },
        temperature: {
            activate: function (newValue) {
                var temperature = restService.createTemperature(newValue, 'celsius');
                restService.putWithData(kettleResourceUrl + '/temperature', temperature);
            }
        }
    };

});

manualBrewing.controller('ManualBrewingMonitorCtrl', function ($scope, $rootScope, refreshService) {
    refreshService.refreshReadings($rootScope.resourcePath + 'brewer/kettle');

    connect();

    function connect() {
        var websocket = new WebSocket($rootScope.wsUri + 'brewer/monitor');
        websocket.onmessage = function (evtJson) {
            console.log(evtJson.data);
            var evt = JSON.parse(evtJson.data);
            switch (evt.event) {
                case 'ingredient added':
                    $rootScope.brewer.readings.capacity += evt.ingredient.volume.value;
                    break;
                case 'temperature changed':
                    $rootScope.brewer.readings.temperature = evt.temperature.value;
                    break;
                default:
                    console.log('default' + evt);
                    
            }
            $rootScope.$apply();

            $('#messages').append("RECEIVED: " + evtJson.data + '<br/>');
        };
    }
});

manualBrewing.factory("refreshService", function ($rootScope, restService) {
    $rootScope.brewer = {};
    
    return {
        refreshReadings: function (resourcePath) {
            restService.get(resourcePath)
                    .success(function (response) {
                        console.log(response);
                        $rootScope.brewer.readings = {
                            'capacity': response.ingredients.reduce(function (a, b) {
                                return (a.volume && a.volume.value || a) + b.volume.value;
                            }, 0),
                            'temperature': response.temperature.value,
                            'locked': 0
                        };
                    }).error(function (response) {
                $rootScope.brewer.readings = {
                    'capacity': 0,
                    'temperature': 0,
                    'locked': 0
                };
            });
        }
    };
});