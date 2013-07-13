<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bootstrap, from Twitter</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">     
    <link rel="shortcut icon" type="image/x-icon" href="http://twitter.com/favicons/favicon.ico">      
    <link rel="stylesheet" href="http://twitter.github.io/typeahead.js/css/examples.css"> 

    <!-- Le styles -->
    <link href=" http://twitter.github.io/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
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
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">Liri</a>
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
                    <h2 class="example-name">Speak to Liri!</h2>        
                    <div class="demo"> <input id="target" class="typeahead" type="text" placeholder="">  </div>          
                        </div> 

       <br>
    
       <div id = "profile-container" >
        <ul id = "people_list" class = "thumbnails">
        </ul>
       </div>
    </div> 

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src=" http://twitter.github.io/bootstrap/assets/js/jquery.js"></script>
    <script src=" http://twitter.github.io/bootstrap/assets/js/bootstrap-transition.js"></script>
    <script src=" http://twitter.github.io/bootstrap/assets/js/bootstrap-alert.js"></script>
    <script src=" http://twitter.github.io/bootstrap/assets/js/bootstrap-modal.js"></script>
    <script src=" http://twitter.github.io/bootstrap/assets/js/bootstrap-dropdown.js"></script>
    <script src=" http://twitter.github.io/bootstrap/assets/js/bootstrap-scrollspy.js"></script>
    <script src=" http://twitter.github.io/bootstrap/assets/js/bootstrap-tab.js"></script>
    <script src=" http://twitter.github.io/bootstrap/assets/js/bootstrap-tooltip.js"></script>
    <script src=" http://twitter.github.io/bootstrap/assets/js/bootstrap-popover.js"></script>
    <script src=" http://twitter.github.io/bootstrap/assets/js/bootstrap-button.js"></script>
    <script src=" http://twitter.github.io/bootstrap/assets/js/bootstrap-collapse.js"></script>
    <script src=" http://twitter.github.io/bootstrap/assets/js/bootstrap-carousel.js"></script>
    <script src=" http://twitter.github.io/bootstrap/assets/js/bootstrap-typeahead.js"></script>
    <script src="http://twitter.github.io/typeahead.js/js/hogan-2.0.0.js"></script>     
    <script src="http://twitter.github.io/typeahead.js/js/jquery-1.9.1.min.js"></script>     
    <script src="http://twitter.github.io/typeahead.js/releases/latest/typeahead.js">      </script>     
    <script>

var postToServer = function(url) {
  var data;
  $.ajax({
  dataType: "json",
  url: "http://localhost:8081/it?query="+encodeURIComponent(url),
  data: data,
  success: function(data, status, xhr) {
  	$("#people_list").empty();
    data = data.values; 
    for(var i=0;i<data.length;i++) {
    $("#people_list").append('<li class="span4"><div class="thumbnail" style="float:left"><img src="'+data[i].pictureUrl+'"/></div><div style="float:left;margin-left:20px"><p align="left">'+data[i].formattedName+'<br/>'+"Amazon Inc"+'<br/>'+"Bengaluru"+'</p>'+'</div></li>');
	}
  }
  });
}
$('#target').bind('keypress',function(e) {
  if(e.keyCode==13)
  {
  	var val = this.value;
    postToServer(val);
  }
});

    </script>

  </body>
</html>

