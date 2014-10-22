var restService = angular.module('breweryApp');

restService.factory('restService', function ($http) {
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
        del: function (url) {
            return $http({
                method: 'DELETE',
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
        createIngredient: function (name, value, unit) {
            return {
                'name': name,
                'volume': {
                    'value': value,
                    'unit': unit
                }
            };
        },
        createTemperature: function (value, unit) {
            return {
                'value': value,
                'unit': unit
            };
        },
        createWaitingMessage: function (object) {
            return {
                'value': object.value,
                'unit': object.unit
            };
        }
    };
});