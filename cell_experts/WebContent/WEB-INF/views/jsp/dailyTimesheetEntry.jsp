<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<div class="container">
	<body>

		<table class="table">
			<caption>Employee Name: ${emp.firstName} ${emp.lastName}</caption>
			<thead>

				<tr>
					<th>#</th>

					<th>today's day</th>

					<th>hours</th>
					<th>Minutes</th>
					<th>Cash</th>
					<th>Notes</th>
				</tr>

			</thead>
			<tbody>
				<tr>
					<th scope="row">#</th>
					<td>
						<div class="form-group">
							<div class="col-sm-10">
								<input type="text" class="form-control" path="day" id="day">
							</div>
						</div>
					</td>
					<td>
						<div class="form-group">
							<div class="col-sm-10">
								<input type="text" class="form-control" path="hours" id="hours">
							</div>
						</div>
					</td>
					<td>
						<div class="form-group">
							<div class="col-sm-10">
								<input type="text" class="form-control" id="minutes">
							</div>
						</div>
					</td>
					<td>
						<div class="form-group">
							<div class="col-sm-10">
								<input type="text" class="form-control" id="cash">
							</div>
						</div>
					</td>
					<td>
						<div class="form-group">
							<div class="col-sm-15">
								<input type="text" class="form-control" id="note">
							</div>
						</div>
					</td>

				</tr>

			</tbody>
		</table>
		<div>
			<div>
				<input type="submit" value="submit Time">
			</div>
		</div>
	</body>
</div>
</html>