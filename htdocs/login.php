<html>
<head>
  <meta charset="utf-8">
    <title>Trinity - Searchin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">     
    <link rel="shortcut icon" type="image/x-icon" href="http://twitter.com/favicons/favicon.ico">      
    <link rel="stylesheet" href="http://twitter.github.io/typeahead.js/css/examples.css"> 

    <!-- Le styles -->
    <link rel="stylesheet" type="text/css" href="css/metro-bootstrap.css">
    <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }

      #main {
          margin:0 auto;
          float:none;
             }

      h1, h2, h3, h4, h5, h6 {
        text-align: center;
    }

    textarea { resize:none ;font-size:20px;font-family: Consolas, Lucida Console, monospace; }
    </style>
    <link href=" http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src=" http://twitter.github.io/bootstrap/assets/js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href=" http://twitter.github.io/bootstrap/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href=" http://twitter.github.io/bootstrap/assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href=" http://twitter.github.io/bootstrap/assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href=" http://twitter.github.io/bootstrap/assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href=" http://twitter.github.io/bootstrap/assets/ico/favicon.png">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>     

  <script src="http://platform.linkedin.com/in.js">
    api_key: xy6upwkl4yrd
    authorize: true
    credentials_cookie: true
  </script>

  <script>
    // Once we have an authorization, pass back the token
    function onLinkedInAuth() {
         // this must be the same domain as the application, where we write the cookie
         //$.post('https://localhost:8449/hello');
		 window.location = 'https://localhost:8449/hello';
    }
  </script>
</head>
<body>


    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">trInity</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="#about">About</a></li>
              
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">
      <div class="example example-countries"> 
                    <img src="icon.png" width ="480" height="180" e/>               
					  
                    <div> <script type="in/login" data-onAuth="onLinkedInAuth"></script>  </div>          
                        </div> 

       <br>
    </div> 

    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="docs/jquery-1.8.0.js"></script>
<script type="text/javascript" src="docs/bootstrap-tooltip.js"></script>
<script type="text/javascript" src="docs/bootstrap-alert.js"></script>
<script type="text/javascript" src="docs/bootstrap-button.js"></script>
<script type="text/javascript" src="docs/bootstrap-carousel.js"></script>
<script type="text/javascript" src="docs/bootstrap-collapse.js"></script>
<script type="text/javascript" src="docs/bootstrap-dropdown.js"></script>
<script type="text/javascript" src="docs/bootstrap-modal.js"></script>
<script type="text/javascript" src="docs/bootstrap-popover.js"></script>
<script type="text/javascript" src="docs/bootstrap-scrollspy.js"></script>
<script type="text/javascript" src="docs/bootstrap-tab.js"></script>
<script type="text/javascript" src="docs/bootstrap-transition.js"></script>
<script type="text/javascript" src="docs/bootstrap-typeahead.js"></script>
<script type="text/javascript" src="docs/jquery.validate.js"></script>
<script type="text/javascript" src="docs/jquery.validate.unobtrusive.js"></script>
<script type="text/javascript" src="docs/jquery.unobtrusive-ajax.js"></script>
<script type="text/javascript" src="docs/metro-bootstrap/metro-docs.js"></script>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36060270-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>     
</body>
</html>