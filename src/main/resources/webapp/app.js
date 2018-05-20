angular.module('sortApp', ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);

angular.module('sortApp').controller('mainController', function($scope, $http, $uibModal) {
    $scope.sortType = 'title'; // set the default sort type
    $scope.sortReverse = false; // set the default sort order
    $scope.searchFish = ''; // set the default search/filter term

    $scope.refresh = function(strategy) {
        if (strategy === 'mobile') {
            $scope.pages = $scope.mobile;
        } else {
            $scope.pages = $scope.desktop;
        }
    };


    $scope.open = function(page, mode) {
        $scope.page = page;
        $scope.page.mode = mode;
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'myModalContent.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            size: 'lg',
            resolve: {
                page: function() {
                    return $scope.page;
                }
            }
        });
    };


    $scope.origin = window.location.origin;
    $scope.title = window.location.hash.replace('#', '');
    let website = encodeURIComponent($scope.title);

    if (website && website.indexOf('http') > -1) {
        $scope.loading = true;

        $http.get(`${$scope.origin}/api/v1/website/mobile/${website}`)
            .then(function(response) {
                $scope.loading = false;
                $scope.mobile = response.data.data.pages;
                $scope.pages = $scope.mobile;
                $scope.title = response.data.data.title;
            }, function(error) {
                $scope.loading = false;
                console.log(error.data);
                 $scope.error = error.data;
             });

        $http.get(`${$scope.origin}/api/v1/website/desktop/${website}`)
            .then(function(response) {
                $scope.desktop = response.data.data.pages;
                $scope.title = response.data.data.title;
            }, function(error) {
                  $scope.loading = false;
                  console.log(error.data);
                  $scope.error = error.data;
              });
    } else {
        $scope.noUrl = true;
    }

});

angular.module('sortApp').controller('ModalInstanceCtrl', function(page) {
    var $ctrl = this;
    $ctrl.page = page;
});