'use strict';

angular.module('breweryApp')
    .controller('RestCtrl', function ($scope, restService) {
        console.log($scope);
        console.log(restService.fullName);
    });