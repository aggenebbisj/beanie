angular.module('breweryApp').controller('ManualBrewingCtrl', function ($scope, $rootScope, restService, refreshService) {
    'use strict';
    var ingredientsResourceUrl = $rootScope.resourcePath + 'brewer/ingredients';
    var kettleResourceUrl = $rootScope.resourcePath + 'brewer/kettle';
    $scope.recipe = [];
    
    $scope.operations = {
        flush: {
            activate: function () {
                restService.del(ingredientsResourceUrl);
                refreshService.refreshReadings(kettleResourceUrl);
            }
        },
        ingredient: {
            activate: function (type, newValue) {
                if (newValue > 0) {
                    var ingredient = restService.createIngredient(type, newValue, 'liter');
                    restService.postWithData(ingredientsResourceUrl, ingredient);
                }
            },
            addToRecipe: function(type, newValue) {
                if (newValue > 0) {
                    var ingredient = restService.createIngredient(type, newValue, 'liter');
                    $scope.recipe.push({ 'type': 'addIngredient', 'ingredient': ingredient, 'description': ingredient.description() });
                }
            }
        },
        temperature: {
            activate: function (value) {
                var temperature = restService.createTemperature(value, 'celsius');
                restService.putWithData(kettleResourceUrl + '/temperature', temperature);
            },
            addToRecipe: function (value) {
                var temperature = restService.createTemperature(value, 'celsius');
                $scope.recipe.push({ 'type': 'changeTemperature', 'temperature': temperature, 'description': temperature.description() });
            }
        },
        wait: {
            activate: function (value) {
                var duration = restService.createDuration(value, 'minutes');
                restService.putWithData(kettleResourceUrl + '/temperature/stable', duration);
            },
            addToRecipe: function (value) {
                var duration = restService.createDuration(value, 'minutes');
                $scope.recipe.push({ 'type': 'keepTemperatureStable', 'duration': value, 'description': duration.description() });
            }
        }
    };

});



