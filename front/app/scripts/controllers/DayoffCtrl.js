"use strict";

angular.module('sprintifier')
	.controller('DayoffCtrl', [
	
		'$scope',
		'$state',
		'DayoffFactory',
	
		function($scope, $state, DayoffFactory) {
      
      $scope.daoyffs = [];

      $scope.initCalendar = function() {
        console.log('test');
          $('#calendar').fullCalendar({
            lang: 'ru',
            header: {
              left: 'prev,next today',
              center: 'title',
              right: 'month,basicWeek'
            },
            firstDay: 1,
            height: 500,
            editable: true,
            eventLimit: true, // allow "more" link when too many events
            editable: false,

            eventRender: function(calEvent, element) {
              element.attr('title', calEvent.title);
            //   element.bind('click', function() {
            //     clickEvent(calEvent);
            // });
            
          }
        });
      }

      $scope.getEventForCalendar = function(){
        
        DayoffFactory.getAll()
          .then(function(response){
            
            $scope.dayoffs=response.data.data;
            
            angular.forEach($scope.dayoffs, function(value){
              value.start = value.day;
              if(value.isHoliday) {
                value.color = "green";
              } else {
                value.color= "gray";
              }
            });
            $('#calendar').fullCalendar('addEventSource', $scope.dayoffs);
        })
      }

    }
	]);