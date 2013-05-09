var sprints = [];
var points = [];

var average = [];
var pointsInBacklog = [];
var sprintsToDo = [];

$(function () {

	$.getJSON(
		'statistics',
		 'getJSONSprints=true',
			function(data){
				$.each(data,
					function(index, sprint){
						sprints.push("Sprint " + sprint.id);
						points.push(sprint.donePoints);
				});

				createChart();
			}
		);

	$.getJSON(
			'statistics',
			'getJSONStemp=true',
			function(data){
				$.each(data,
						function(index,temp){
							average.push(temp.average);
							pointsInBacklog.push(temp.pointsInBacklog);
							sprintsToDo.push(temp.sprintsToDo);
					});

				temp();
			}
	);
});



function createChart() {

   chart = new Highcharts.Chart({
        chart: {
            renderTo: 'container',
            type: 'line',
            marginRight: 130,
            marginBottom: 25
        },
        title: {
            text: 'Pontos X Sprints',
            x: -20 //center
        },
        xAxis: {
            categories: sprints
        },
        yAxis: {
            title: {
                text: 'Pontos'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+
                    this.x +': '+ this.y +' Pontos';
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -10,
            y: 100,
            borderWidth: 0
        },
        series: [{
            name: 'Pontos',
            data: points
        }]
    });
}






function temp() {


		chart = new Highcharts.Chart({
	    chart: {
	        renderTo: 'container2',
	        type: 'bar'
	    },
	    title: {
	        text: 'Media X Backlog X Sprints'
	    },
	    xAxis: {
	        categories: sprints,
	        title: {
	            text: null
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: 'Pontos',
	            align: 'high'
	        },
	        labels: {
	            overflow: 'justify'
	        }
	    },
	    tooltip: {
	        formatter: function() {
	            return ''+ this.series.name +': '+ this.y ;
	        }
	    },
	    plotOptions: {
	        bar: {
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'top',
	        x: -100,
	        y: 100,
	        floating: true,
	        borderWidth: 1,
	        backgroundColor: '#FFFFFF',
	        shadow: true
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{
	        name: 'Media de pontos por Sprint',
	        data: average
	    }, {
	        name: 'Total de pontos no BackLog',
	        data: pointsInBacklog
	    }, {
	        name: 'Total de Sprints a fazer',
	        data: sprintsToDo
	    }]
	});
}



