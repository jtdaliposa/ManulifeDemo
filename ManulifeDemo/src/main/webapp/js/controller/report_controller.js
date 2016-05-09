myApp.controller('ReportController', [
		'$scope',
		'ReportService',
		function($scope, ReportService) {
			var self = this;
			self.labels=[];
			self.data=[];
			self.displayLimit=5;
			self.website = {
				id : null,
				name : '',
				url : ''
			};
			self.websites = [];
			self.report = {
				startDate : '',
				endDate : '',
				websiteName : '',
				totalVisits : null
			};
			self.reports = [];
			self.myDataSource;

			self.fetchAllWebsites = function() {
				ReportService.fetchAllWebsites().then(function(d) {
					self.websites = d;
				}, function(errResponse) {
					console.error('Error while fetching Results');
				});
			};

			self.getReport = function() {
				ReportService.getReports(self.report.startDate,
						self.report.endDate, self.report.websiteName).then(
							function(d) {
								self.reports = d;
								console.log(d);
								for(var i=0; i < d.length; i++){
									console.log(self.labels[i]);
									self.labels[i]=d[i].websiteName;
									self.data[i]=d[i].totalVisits;
								}
								self.generateChart();
							}, function(errResponse) {
								console.error('Error while fetching Results');
				});
				
			};

			self.getReport();
			
			self.generateChart = function(){
				self.data=[];
				for(var i=0; i<self.reports.length;i++){
					self.data[i]={
							"label":self.reports[i].websiteName,
							"value":self.reports[i].totalVisits
							};
				}
				var subCaption = "All time record"
				if(self.report.endDate!='' && self.report.startDate){
					subCaption = "From "+self.report.startDate+" to "+self.report.endDate;
				}
				
		        var revenueChart = new FusionCharts({
		            "type": "bar2d",
		            "renderAt": "chartContainer",
		            "width": "500",
		            "height": "300",
		            "dataFormat": "json",
		            "dataSource": {
		                "chart": {
		                    "caption": "Website Ranking by Visits",
		                    "subCaption": subCaption,
		                    "yAxisName": "Visits",
		                    "paletteColors": "#0075c2",
		                    "bgColor": "#ffffff",
		                    "showBorder": "0",
		                    "showCanvasBorder": "0",
		                    "usePlotGradientColor": "0",
		                    "plotBorderAlpha": "5",
		                    "placeValuesInside": "1",
		                    "valueFontColor": "#ffffff",
		                    "showAxisLines": "1",
		                    "axisLineAlpha": "25",
		                    "divLineAlpha": "10",
		                    "alignCaptionWithCanvas": "0",
		                    "showAlternateVGridColor": "0",
		                    "captionFontSize": "14",
		                    "subcaptionFontSize": "14",
		                    "subcaptionFontBold": "0",
		                    "toolTipColor": "#ffffff",
		                    "toolTipBorderThickness": "0",
		                    "toolTipBgColor": "#000000",
		                    "toolTipBgAlpha": "80",
		                    "toolTipBorderRadius": "2",
		                    "toolTipPadding": "5"
		                },
		                "data": self.data
		            }
		        });
		        revenueChart.render("chartContainer");
		      };
			

		} ]);