var app = angular.module('headerMenuApp', []);

app.controller('Ctrl', function($scope, $http){
    $http.get('/refreshMessageNotifications').
    then(function(response) {
        $scope.messageNotifications = response.data;
    });
    $scope.notificationRefresh  = function(){
        $http.get('/refreshMessageNotifications').
        then(function(response) {
            $scope.messageNotifications = response.data;
        });
    }
});