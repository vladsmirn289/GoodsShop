<#macro commonPage>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>GoodsShop</title>

    <link type="text/css" rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="/css/common.css"/>
</head>

<body>
<#include "navbar.ftl">
<div class="container mt-5">
    <#nested>
</div>

<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="/webjars/popper.js/1.16.0/umd/popper.min.js"></script>
</body>

</html>
</#macro>