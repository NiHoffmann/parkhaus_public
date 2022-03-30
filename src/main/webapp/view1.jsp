<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xml:lang="de">
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <script src='https://ccmjs.github.io/mkaul-components/parkhaus/versions/ccm.parkhaus-10.1.1.js'></script>
</head>
<body>
<ccm-parkhaus-10-1-1
        server_url="http://localhost:8080/Parkhaus_war_exploded/Parkhaus"
        extra_buttons='["Tageseinnahmen"]'
></ccm-parkhaus-10-1-1>
<a href="http://localhost:8080/Parkhaus_war_exploded/index.jsp"><button type="button">Back</button></a>
<div id='chart'></div>

</body>
</html>