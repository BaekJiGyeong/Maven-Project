<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--<c:set var="root" value="${pageContext.request.contextPath}" /> --%>
<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie9"><![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Education Calendar</title>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/vendors/auto-size/jquery.autosize.min.js"></script>
<script type="text/javascript" src="/resources/vendors/fileinput/fileinput.min.js"></script>
<script type="text/javascript" src="/resources/vendors/input-mask/input-mask.min.js"></script>
<script type="text/javascript" src="/resources/vendors/farbtastic/farbtastic.min.js"></script>
<script type="text/javascript" src="/resources/vendors/summernote/summernote.min.js"></script>
<script type="text/javascript" src="/resources/vendors/noUiSlider/jquery.nouislider.all.min.js"></script>
<script type="text/javascript" src="/resources/vendors/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="/resources/vendors/bootstrap-select/bootstrap-select.min.js"></script>
<script type="text/javascript" src="/resources/vendors/moment/moment.min.js"></script>
<script type="text/javascript" src="/resources/vendors/nicescroll/jquery.nicescroll.min.js"></script>
<script type="text/javascript" src="/resources/vendors/fullcalendar/lib/moment.min.js"></script>
<script type="text/javascript" src="/resources/vendors/fullcalendar/fullcalendar.min.js"></script>
<script type="text/javascript" src="/resources/vendors/waves/waves.min.js"></script>
<script type="text/javascript" src="/resources/vendors/bootstrap-growl/bootstrap-growl.min.js"></script>
<script type="text/javascript" src="/resources/vendors/sweet-alert/sweet-alert.min.js"></script>
<script type="text/javascript" src="/resources/vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">
	$(document).ready(
					function() {
						var today = new Date();
						var date = new Date();
						var start = new Date();
						var end = new Date();
						var d = date.getDate();
						var m = date.getMonth();
						var y = date.getFullYear();

						var cId = $('#calendar'); //Change the name if you want. I'm also using thsi add button for more actions

						var calNotices = new Array();
						<c:forEach items="${ calNotice }" var="notice" >
						var calNotice = {};
						calNotice.id = "${notice.calendarId}"
						calNotice.title = "${notice.title}";
						calNotice.description = "${notice.description}";
						calNotice.start = "${notice.startDate}";
						calNotice.end = "${notice.endDate}";
						calNotice.className = "${notice.className}";
						calNotices.push(calNotice);
						</c:forEach>
						/* 		console.log(calNotices[0].title)
								console.log(calNotices[0].description)
								console.log(calNotices[0].start)
								console.log(calNotices[0].end)
								console.log(calNotices[0].className)
								console.log(calNotices[0].id)
						 */
						//Generate the Calendar
						cId.fullCalendar({
									header : {
										right : '',
										center : 'prev, title, next',
										left : ''
									},
									defaultView : 'month',
									dayRender : function(date, cell) {
										
										end.setDate(today.getDate() + 7);
										var cellDate = $(cell).data('date');
										var todayDate;

										if (m + 1 < 10) {
											if (d < 10) {
												todayDate = y + '-' + '0' + (m + 1) + '-' + '0' + d;
											} 
											else {
												todayDate = y + '-' + '0' + (m + 1) + '-' + d;
											}
										} 
										else {
											if (d < 10) {
												todayDate = y + '-' + (m + 1) + '-' + '0' + d;
											} 
											else {
												todayDate = y + '-' + (m + 1) + '-' + d;
											}
										}
										console.log("cellDate" + cellDate);
										console.log("todayDate" + todayDate);

										if (cellDate == todayDate) {
											
											cell.css("background-color", "#a5cefc");
										}
									},

									fixedWeekCount : false,
									contentHeight : 600,
									eventLimit : true,
									theme : true, //Do not remove this as it ruin the design
									selectable : true,
									selectHelper : true,
									select : true,
									editable : true,

									//Add Events
									events : calNotices,
									//Cancel Event
									eventRender : function(event, element) {
										console.log(element);
										var e = element.prepend("<span class='closeon'>&#10005;</span>");
										//element.find('.fc-title').append("<br/>" + event.description);

										e.children('.closeon').attr('data-event-id', event._id).click(
														function() {
															var id = $(this).attr('data-event-id');
															alert(id);
															if (id != '') {
																$.post(
																		"/delete",
																			{
																				"id" : id,
																			},
																				function(d) {
																					var jsonData2 = {};
																					try {
																						jsonData2 = JSON.parse(d);
																						
																						if (jsonData2.deleteNotice) {
																							alert("공지 관련 내용이 삭제되었습니다.");
																							location.href = "/goMain";
																						} 
																						else {}
																					} catch (e) {
																						alert("예상치 못한 에러가 발생했습니다. 관리자에게 문의하세요.");
																					}
																				});
																$('#calendar').fullCalendar(
																		
																				'removeEvents',
																				{
																					id : id
																				},
																				true);
																$('.closeon form')[0].reset();
																$('.closeon').modal('hide');
															}
														});
									},

									//On Day Select
									select : function(start, end, allDay) {
										$('#addNew-event').modal('show');
										$('#addNew-event input:text').val('');
										$('#addContent input:text').val('');
										$('#current input:text').val('');
										$('#getStart').val(start);
										$('#getEnd').val(end);
										var moment = $('.callender_area').fullCalendar('stickySource.title');
									},
									eventClick : function(calEvent, jsEvent, view) {
										// $('#modify-event').modal('show');
										/* $('#current input:text').val(calEvent.title);
										$('#modifyContent input:text').val(calEvent.description);
										$('#modifyGetStart').val(calEvent.start);
										$('#modifyGetEnd').val(calEvent.end);
										$('#calendarId').val(calEvent.id); */
									}
								});

						/*  var today ='<ul class="actions actions-alt" id="todays">' +
						 			'</ul>'; */

						//Hightlight Event
						function toggleClass(id) {
							/* Find all segments for the specific event and toggle a class */
							var $event = $('a.fc-event[data-id="' + id
									+ '"]');
							$('a.my-highlight').each(function() {
								$(this).toggleClass('my-highlight');
							});
							$.each($event, function() {
								$(this).toggleClass('my-highlight');
							});
						}

						//Event Tag Selector
						(function() {
							$('body').on('click', '.event-tag > span',
									function() {
										$('.event-tag > span').removeClass('selected');
										$(this).addClass('selected');
									});
						})();

						//Add new Event
						$('body').on(
										'click',
										'#addEvent',
										function() {
											var newNoticeName = $('#eventName').val();
											var description = $('#newCotents').val();
											var tagColor = $('.event-tag > span.selected').attr('data-tag');
											/*  var startDate = $('#getStart').val();
											 var endDate = $('#getEnd').val(); */
											var startDate = $('#date').val();
											var endDate = $('#date').val();
											alert(startDate);

											if (newNoticeName != '') {
												$.post(
															"/calendar",
																{
																	"title" : newNoticeName,
																	"description" : description,
																	"className" : tagColor,
																	"getStart" : startDate,
																	"getEnd" : endDate,
																},
																function(data) {
																	var jsonData3 = {};
																	try {
																		jsonData3 = JSON
																				.parse(data);
																		if (jsonData3.updateNotice) {

																			alert("정보가 입력되었습니다.");
																			location.href = "/goMain";
																		} else {
																		}
																	} catch (e) {
																		alert("예상치 못한 에러가 발생했습니다. 관리자에게 문의하세요.");
																	}
																});
												//Render Event
												$('#calendar').fullCalendar(
																'renderEvent',
																{
																	title : newNoticeName,
																	description : description,
																	start : $('#getStart').val(),
																	end : $('#getEnd').val(),
																	allDay : true,
																	className : tagColor
																}, true); //Stick the event
												$('#addNew-event form')[0].reset();
												$('#addNew-event').modal('hide');

											} else {
												$('#eventName').closest('.form-group').addClass('has-error');
											}
										});

						//today click
						$('#my-today-button').click(function() {
							$('#calendar').fullCalendar('today');
						});

						//Calendar views
						$('body').on(
								'click',
								'#fc-actions [data-view]',
								function(e) {
									e.preventDefault();
									var dataView = $(this).attr('data-view');

									$('#fc-actions li').removeClass('active');
									$(this).parent().addClass('active');
									cId.fullCalendar('changeView', dataView);
								});

						$('body').on('click', '#addButton', function(e) {

							var button = $("#addButton");
							button.attr("data-target", "#addNew-event");
						});

					});
</script>

<!-- Vendor CSS -->
<link href="/resources/vendors/animate-css/animate.min.css" rel="stylesheet">
<link href="/resources/vendors/fullcalendar/fullcalendar.css" rel="stylesheet">
<link href="/resources/vendors/sweet-alert/sweet-alert.min.css" rel="stylesheet">
<link href="/resources/vendors/material-icons/material-design-iconic-font.min.css" rel="stylesheet">
<link href="/resources/vendors/socicon/socicon.min.css" rel="stylesheet">
<link href="/resources/vendors/noUiSlider/jquery.nouislider.min.css" rel="stylesheet">
<link href="/resources/vendors/farbtastic/farbtastic.css" rel="stylesheet">
<link href="/resources/vendors/summernote/summernote.css" rel="stylesheet">
<!-- CSS -->
<link href="/resources/css/app.min.1.css" rel="stylesheet">
<link href="/resources/css/app.min.2.css" rel="stylesheet">
<link href="/resources/css/bottom.css" rel="stylesheet">

</head>

<body>
	<section id="main">
		<section id="cal_content">
			<div class="container">
				<div id="calendar"></div>
				<!-- Add event -->
				<div class="modal fade" id="addNew-event" data-backdrop="static" data-keyboard="false">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Add an Event</h4>
							</div>
							<div class="modal-body">
								<form class="addEvent" role="form">
									<div class="form-group">
										<div>
											<label class="input-group-add" style="font-weight: bold; float: left;">Date</label><br />
											<div class="row" style="float: left; padding-left: 3px;">
												<div class="input-group form-group">
													<span class="input-group-addon"><i class="md md-event"></i></span>
													<div class="dtp-container dropdown fg-line">
														<input type='text' id="date"
															class="form-control date-picker" data-toggle="dropdown" placeholder="Click here...">
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="eventName">Event Name</label>
										<div id="addNotice">
											<input type="text" class="input-sm form-control"
												id="eventName" name="title" placeholder="새로운 공지를 입력하세요.">
										</div>
									</div>
									<div class="form-group">
										<label for="eventName">contents</label>
										<div id="addContent">
											<input type="text" class="input-sm form-control"
												id="newCotents" name="content">
										</div>
									</div>

									<div class="form-group">
										<label for="eventName">Tag Color</label>
										<div class="event-tag">
											<span data-tag="bgm-teal" class="bgm-teal selected"
												name="className"></span> <span data-tag="bgm-red"
												class="bgm-red" name="className"></span> <span
												data-tag="bgm-pink" class="bgm-pink" name="className"></span>
											<span data-tag="bgm-blue" class="bgm-blue" name="className"></span>
											<span data-tag="bgm-lime" class="bgm-lime" name="className"></span>
											<span data-tag="bgm-green" class="bgm-green" name="className"></span>
											<span data-tag="bgm-cyan" class="bgm-cyan" name="className"></span>
											<span data-tag="bgm-orange" class="bgm-orange"
												name="className"></span> <span data-tag="bgm-purple"
												class="bgm-purple" name="className"></span> <span
												data-tag="bgm-gray" class="bgm-gray" name="className"></span>
											<span data-tag="bgm-black" class="bgm-black" name="className"></span>
										</div>
									</div>

									<input type="hidden" id="getStart" /> <input type="hidden" id="getEnd" />
								</form>
							</div>

							<div class="modal-footer">
								<button type="submit" class="btn btn-link" id="addEvent">Add Event</button>
								<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</section>
</body>
</html>