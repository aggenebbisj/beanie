angular.module('breweryApp').factory('restService', function ($http) {
    'use strict';
    return {

        get: function (url) {
            return $http({
                method: 'GET',
                url: url
            });
        }
    };
});