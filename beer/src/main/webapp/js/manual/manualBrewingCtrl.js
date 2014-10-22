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



