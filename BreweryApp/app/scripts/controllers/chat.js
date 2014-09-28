'use strict';

/**
 * @ngdoc function
 * @name breweryApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the breweryApp
 */
angular.module('breweryApp')
    .controller('ChatCtrl', function ($scope) {
        var sock = new SockJS('http://localhost/chat');
        $scope.messages = [];
        $scope.sendMessage = function () {
            sock.send($scope.messageText);
            $scope.messageText = '';
        };

        sock.onmessage = function (e) {
            $scope.messages.push(e.data);
            $scope.$apply();
        };
    });