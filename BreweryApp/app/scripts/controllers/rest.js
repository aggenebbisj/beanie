'use strict';

angular.module('breweryApp')
    .controller('RestCtrl', function ($scope, restService, $rootScope) {

        $scope.recipeSteps = [];

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

        function sendAsPostAndReport(url) {
            restService.post(url)
                .success(function (response) {
                    if (angular.isObject(response)) {
                        addToRecipe(response, true);
                        console.log("success: " + response);
                    }
                }).error(function (response) {
                    addToRecipe(response, false);
                    console.log("failed:  " + response);

                });
        }

//        getUrlAsObject('http://localhost:9000');
//        console.log($scope.test);


    });