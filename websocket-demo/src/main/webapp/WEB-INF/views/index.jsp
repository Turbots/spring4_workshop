<!DOCTYPE HTML>
<html lang="en">
<head>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css"/>
    <link href="assets/style.css" rel="stylesheet" type="text/css"/>
</head>
<body ng-app="chatApp">
<div ng-controller="ChatCtrl" class="container">
    <form ng-submit="addMessage()" name="messageForm">
        <input type="text" placeholder="Write a new message..." ng-model="message"/>

        <div class="info">
            <span class="count" ng-bind="max - message.length">140</span>
            <button>Send</button>
        </div>
    </form>
    <hr/>
    <p ng-repeat="message in messages | orderBy:'time':true" class="message">
        <time>{{message.time | date:'HH:mm'}}</time>
        <span ng-class="{self: message.self}">{{message.message}}</span>
    </p>
</div>

<script src="webjars/sockjs-client/0.3.4-1/sockjs.min.js" type="text/javascript"></script>
<script src="webjars/stomp-websocket/2.3.1-1/stomp.min.js" type="text/javascript"></script>
<script src="webjars/angularjs/1.3.13/angular.min.js"></script>
<script src="webjars/lodash/3.1.0/lodash.min.js"></script>
<script src="app/app.js" type="text/javascript"></script>
<script src="app/controllers.js" type="text/javascript"></script>
<script src="app/services.js" type="text/javascript"></script>
</body>
</html>