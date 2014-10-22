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
            activate: function (value) {
                var temperature = restService.createTemperature(value, 'celsius');
                restService.putWithData(kettleResourceUrl + '/temperature', temperature);
            }
        },
        wait: {
            activate: function (value) {
                var duration = restService.createDuration(value, 'minutes');
                restService.putWithData(kettleResourceUrl + '/temperature/stable', duration);
            }
        }
    };

});



