angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {

    if ($localStorage.marketUser){
        $http.defaults.headers.common.Authorization = 'Bearer '+ $localStorage.marketUser.token;
    }

    $scope.fillTable = function () {
        $http.get('http://localhost:7777/core/api/v1/products')
            .then(function (response) {
                //$scope.products = response.data;
                $scope.products = response.data;
            });
        $http.get('http://localhost:7777/cart/api/v1/cart')
            .then(function (response) {
                //$scope.products = response.data;
                $scope.cart = response.data;
                console.log($scope.cart);
            });
    };
    $scope.deleteProduct=function (id){
        $scope.removeItem(id);
        $http.delete('http://localhost:7777/core/api/v1/products/'+id)
            .then(function (response){
                $scope.fillTable();
            });
    };

    $scope.addProductAmount=function (id){
        $http.get('http://localhost:7777/cart/api/v1/cart/add/'+id)
            .then(function (response){
                $scope.fillTable();
            });
    };

    $scope.subProductAmount=function (id){
        $http.get('http://localhost:7777/cart/api/v1/cart/sub/'+id)
            .then(function (response){
                $scope.fillTable();
            });
    };

    $scope.addProductToCart=function (id){
        $http.get('http://localhost:7777/cart/api/v1/cart/add/'+id)
            .then(function (response){
                $scope.fillTable();
            });
    };

    $scope.removeItem=function (id){
        $http.delete('http://localhost:7777/cart/api/v1/cart/remove/'+id)
            .then(function (response){
                $scope.fillTable();
            });

    };

    $scope.createNewProduct = function () {
        // console.log($scope.newProduct);
        $http.post('http://localhost:7777/core/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }

    $scope.tryToAuth = function () {
        $http.post('http://localhost:7777/auth/authenticate', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.marketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
    };

    $scope.clearUser = function () {
        delete $localStorage.marketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.marketUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.createOrder=function(){
        $http.post('http://localhost:7777/core/api/v1/orders/create')
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.fillTable();
});