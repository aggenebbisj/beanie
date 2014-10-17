angular.module('breweryApp').factory('restService', function ($http) {
    'use strict';
    return {

        get: function (url) {
            return $http({
                method: 'GET',
                url: url
            });
        },
        post: function (url) {
            return $http({
                method: 'POST',
                url: url
            });
        },
        postWithData: function (url, object) {
            return $http({
                method: 'POST',
                url: url,
                headers: {'Content-Type': 'application/json'},
                data: object
            });
        },
        putWithData: function (url, object) {
            return $http({
                method: 'PUT',
                url: url,
                headers: {'Content-Type': 'application/json'},
                data: object
            });
        },
        createIngredient: function(object){
           return {
                'ingredient': object.name,
                'volume': {
                    'value': object.value,
                    'unit': object.unit
                }
            };
        },
        createTemperature: function(object) {
            return {
                'value': object.value,
                'unit': object.unit
            }
        },
        createWaitingMessage: function(object) {
            return {
                'value': object.value,
                'unit': object.unit
            }
        }
    };
});