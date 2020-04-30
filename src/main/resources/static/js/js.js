// JavaScript Document

function showCard(cardid,infoid,clsName)
	{
	 var cardList = cardid.parentNode.getElementsByTagName("span");
	  for(i=0;i<cardList.length;i++){
	   if(cardid == cardList[i]) {
			cardList[i].className = clsName + "On";
			eval("document.getElementById('"+infoid + i + "').style.display=\"block\";");
	   }
	   if(cardid != cardList[i]){
			cardList[i].className = clsName + "Off";
			eval("document.getElementById('"+infoid + i + "').style.display=\"none\";"); 
		}
		
	   }
		
	}

//show/hide
	function showOrHide(nodeId,openId,closeId){
		var nodeDisplay = window.document.getElementById(nodeId).style.display;
		if (nodeDisplay=='block'){
			window.document.getElementById(nodeId).style.display='none';
			window.document.getElementById(openId).style.display='block';
			window.document.getElementById(closeId).style.display='none';
		} else if (nodeDisplay=='none'){
			window.document.getElementById(nodeId).style.display='block';
			window.document.getElementById(openId).style.display='none';
			window.document.getElementById(closeId).style.display='block';
		}
		
	}
