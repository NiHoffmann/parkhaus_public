<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <script src='https://ccmjs.github.io/mkaul-components/parkhaus/versions/ccm.parkhaus-10.1.1.js'></script>
</head>
<body>
<ccm-parkhaus-10-1-1
        server_url="http://localhost:8080/Parkhaus_war_exploded/Parkhaus"
        extra_buttons='["sum", "avg", "Besucher", "Preis"]'
        extra_charts='["Anzahl", "Parkverhalten"]'
        vehicle_types='["Truck", "Pickup", "SUV", "Zweirad", "Trike", "Quad"]'
        price_factor='{"Truck":2, "Pickup":1, "SUV":1, "Zweirad":0.5, "Trike":0.5, "Quad":0.5}'
></ccm-parkhaus-10-1-1>
<a href="http://localhost:8080/Parkhaus_war_exploded/view1.jsp"><button type="button">View 1: Manager Tageseinnahmen</button></a>
<a href="http://localhost:8080/Parkhaus_war_exploded/view2.jsp"><button type="button">View 2: Manager Wocheneinnahmen</button></a>
<a href="http://localhost:8080/Parkhaus_war_exploded/view3.jsp"><button type="button">View 3: Kundensicht</button></a>
<div id='chart'></div>
</body>
</html>