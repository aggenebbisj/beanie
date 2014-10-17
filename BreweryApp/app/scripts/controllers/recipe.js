'use strict';

angular.module('breweryApp')
    .controller('RecipeCtrl', function ($scope, restService, $rootScope) {

        $scope.recipeSteps = [];
        $scope.recipeName = '';
        if (typeof $rootScope.serverUrl === 'undefined') {
            $rootScope.serverUrl = 'http://localhost:8080';
            $rootScope.resourcePath = '/brewery/resources/kettle/';
            $rootScope.recipePath = '/brewery/resources/recipe';
        }

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
                        'ingredient': object.name,
                        'volume': {
                            'value': object.value,
                            'unit': object.unit
                        }
                    };
                    addToRecipe(step);
                }},
            water: {
                'operation': 'add',
                'name': 'water',
                'unit': 'liter',
                'value': 0,
                activate: function (object) {
                    var step = {
                        'type': 'addIngredient',
                        'ingredient': object.name,
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
                        'duration': 'PT' + object.value + 'M'
                    };
                    addToRecipe(step);
                }}
        };

        $scope.submitRecipe = function () {
            var recipe = {
                'name': $scope.recipeName,
                'steps': $scope.recipeSteps
            };
            sendAsPost($rootScope.serverUrl + $rootScope.recipePath, recipe);
        };

        function sendAsPost(url, object) {
            restService.postWithData(url, object)
                .success(function (response) {
                    onSuccess(response);
                        console.log('success: ' + response);
                }).error(function (response) {
                    onError(response);
                    console.log('failed:  ' + response);

                });
        }

        function onSuccess(evt) {
            $scope.error.value = true;
            $scope.error.text = evt;
        }

        function onError(evt) {
            $scope.error.value = true;
            $scope.error.text = evt;
        }
    });