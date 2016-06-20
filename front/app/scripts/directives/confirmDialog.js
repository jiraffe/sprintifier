"use strict";

angular.module('sprintifier')
    .directive('confirmDialog', [

        '$uibModal',

        function($uibModal) {
            var ModalInstanceCtrl = function($scope, $uibModalInstance) {
                $scope.ok = function() {
                    $uibModalInstance.close();
                };

                $scope.cancel = function() {
                  $uibModalInstance.dismiss('cancel');
                };
            };

            return {
                restrict: 'A',
                scope: {
                    confirmClick: '&',
                    cancelClick: '&'
                },

                link: function(scope, element, attrs) {

                    var header = attrs.confirmHeader;
                    var message = attrs.confirmMessage;

                    element.bind('click', function() {

                        var modalHtml = '<div class="modal-header confirmDialog">' + 
                        '<button type="button" class="close" data-dismiss="modal" ng-click="cancel()"><span aria-hidden="true" uibTooltip="Закрыть" uibTooltip-placement="left" >&times;</span><span class="sr-only">Close</span></button>' + 
                        '<h4 class="modal-title"><span class="glyphicon glyphicon-check"></span> ' + header + '</h4></div>' +
                        '<div class="modal-body">' + message + '</div>' +
                        '<div class="modal-footer"><button class="btn btn-default" ng-click="ok()">Да</button><button class="btn btn-primary" ng-click="cancel()">Нет</button></div>';

                        var uibModalInstance = $uibModal.open({
                            template: modalHtml,
                            controller: ModalInstanceCtrl,
                            backdrop:"static", 
                            keyboard:false
                        });

                        uibModalInstance.result.then(function() {
                            scope.confirmClick();
                        });
                        
                    });
                }
            };
        }

]);
