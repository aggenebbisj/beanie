'use strict';

angular.module('breweryApp')
    .controller('RestCtrl', function ($scope, restService, $rootScope) {

        $scope.recipeSteps = [];
        $scope.recipeName = '';

        function addToRecipe(step) {
            $scope.recipeSteps.push(step);
        }

        $scope.operations = {
            yeast: {
                'operation': 'add',
                'name': 'yeast',
                'unit': 'liter',
                'value': 0,
                activate: function (object) {
                    var step = {
                        'type': 'addIngredient',
                        'ingredient' : object.name,
                        'volume': {
                            'value': object.value,
                            'unit': object.unit
                        }
                    };
                    addToRecipe(step);
                }},
            temperature: {
                'operation': 'change',
                'unit': 'celsius',
                'name': 'temperature',
                'value': 0,
                activate: function (object) {
                    var step = {
                        'type': 'changeTemperature',
                        'temperature': {
                            'value': object.value,
                            'scale': object.unit
                        }
                    };
                    addToRecipe(step);

                }},
            waiting: {
                'operation': '',
                'unit': 'minutes',
                'name': 'wait',
                'value': 0,
                activate: function (object) {
                    var step = {
                        'type': 'stableTemperature',
                        'duration': 'PT'+ object.value+'M'
                    };
                    addToRecipe(step);
                }}
        };

     $scope.submitRecipe = function(){
         var recipe = {
             'name': $scope.recipeName,
             'steps': $scope.recipeSteps
         };
         sendAsPost($rootScope.serverUrl + "/recipe", recipe)
     }

        function sendAsPost(url, object) {
            restService.postWithData(url, object)
                .success(function (response) {
                    if (angular.isObject(response)) {
                        console.log("success: " + response);
                    }
                }).error(function (response) {
                    console.log("failed:  " + response);

                });
        }
    });