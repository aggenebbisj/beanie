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
                },
                description: function() {
                    return 'Add ' + value + ' ' + unit + 's of ' + name;
                }
            };
        },
        createTemperature: function (value, unit) {
            return {
                'value': value,
                'unit': unit,
                description: function() {
                    return 'Change temperature to ' + value + ' degrees Celsius';
                }
            };
        },
        createDuration: function (value, unit) {
            return {
                'value': value,
                'unit': unit,
                description: function() {
                    return 'Keep temperature stable for ' + value + ' ' + unit;
                }
            };
        }
    };
});