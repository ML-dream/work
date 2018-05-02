var xmlHttp;
var flight_type="";
var product_id="";
var lot="";



function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}
    
function addSelect(type,sid,elementID) {
    //System.out.println(type+" "+sid+" "+elementID);
	//alert(type+" "+sid+" "+elementID);
    //����var��������������������ִ�У��ñ����;�����ȫ����
    if(elementID==null){return;}
	oElement = document.getElementById(elementID);
    s_lot    =document.getElementById("lot");
    s_issuenum=document.getElementById("issue_num")
    
    initSelect(oElement);
    flight_type = document.getElementById("product_type").value;
    product_id  = document.getElementById("product_id").value;
    issue_num = document.getElementById("issue_num").value;
    lot         = document.getElementById("lot").value;

    if(elementID=="product_id"){
       initSelect(s_lot);
       initSelect(s_issuenum);
        s_lot.options[0].innerHTML="--------";
        s_issuenum.options[0].innerHTML="--------";
    }
    if(elementID=="issue_num"){
    	initSelect(s_lot);
    	
    	s_lot.options[0].innerHTML="--------";
        
    }
    
    
    if(sid==""){
        oElement.options[0].innerHTML="--------";
    }else{
        createXMLHttpRequest();
        var url = "bom_select?type="+type+"&flight_type="+flight_type+"&product_id="+product_id+"&issue_num="+issue_num+"&lot="+lot;
        /*��׼��״̬�ı�ʱ����ҪΪreadyState����ָ���¼����������ô����������ִ��ݲ����ķ�����
        xmlhttp.onreadystatechange= function(){HandleStateChange(param1,param2...)}; ����  
        xmlhttp.onreadystatechange=new Function("HandleStateChange(param1,param2...)"); 
        */
        xmlHttp.onreadystatechange = function(){onStateChange()};//,s_product_id,s_lot,s_sortie,s_issue
        xmlHttp.open("POST", url, true);
        xmlHttp.send(null);
    }
}
    
function onStateChange() {
    if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
            showSelect(xmlHttp.responseXML);
        }
    }
}

function showSelect(xmlData) {
        if(xmlData.documentElement.hasChildNodes()){
           oElement.options[0].innerHTML="--��ѡ��--";

           if(oElement==document.getElementById("product_id")){
            var names = xmlData.getElementsByTagName("Name_product_id");
            var ids = xmlData.getElementsByTagName("ID_product_id");
            for(var i = 0; i < names.length; i++) {
            var op=new Option(names[i].firstChild.nodeValue);  
            //Ϊ�б�/�˵����ѡ��ʱ��object.options.add������object.appendChild���������á�   
            oElement.options.add(op);
	        op.value=ids[i].firstChild.nodeValue;
            }
           }
           if(oElement==document.getElementById("issue_num")){
               var names = xmlData.getElementsByTagName("Name_issuenum");
               var ids = xmlData.getElementsByTagName("ID_issuenum");
               for(var i = 0; i < names.length; i++) {
               var op=new Option(names[i].firstChild.nodeValue);  
               //Ϊ�б�/�˵����ѡ��ʱ��object.options.add������object.appendChild���������á�   
               oElement.options.add(op);
   	        op.value=ids[i].firstChild.nodeValue;
               }
              }
           if(oElement==document.getElementById("lot")){
             var names = xmlData.getElementsByTagName("Name_lot");
             var ids = xmlData.getElementsByTagName("ID_lot");
             for(var i = 0; i < names.length; i++) {
             var op=new Option(names[i].firstChild.nodeValue);  
            //Ϊ�б�/�˵����ѡ��ʱ��object.options.add������object.appendChild���������á�   
             oElement.options.add(op);
	         op.value=ids[i].firstChild.nodeValue;
            }
           }
           if(oElement==document.getElementById("sortie")){
             var names = xmlData.getElementsByTagName("Name_sortie");
             var ids = xmlData.getElementsByTagName("ID_sortie");
              for(var i = 0; i < names.length; i++) {
              var op=new Option(names[i].firstChild.nodeValue);  
            //Ϊ�б�/�˵����ѡ��ʱ��object.options.add������object.appendChild���������á�   
             oElement.options.add(op);
	         op.value=ids[i].firstChild.nodeValue;
            }
           }
//           if(oElement==document.getElementById("issue")){
//             var names = xmlData.getElementsByTagName("Name_issue");
//             var ids = xmlData.getElementsByTagName("ID_issue");
//              for(var i = 0; i < names.length; i++) {
//              var op=new Option(names[i].firstChild.nodeValue);  
//            //Ϊ�б�/�˵����ѡ��ʱ��object.options.add������object.appendChild���������á�   
//             oElement.options.add(op);
//	         op.value=ids[i].firstChild.nodeValue;
//            }
//           }

        }else{
          oElement.options[0].innerHTML="��������";

          if(oElement==document.getElementById("product_id")){
           s_lot.options[0].innerHTML="��������";
           
         //  s_issue.options[0].innerHTML="��������";
          }
          if(oElement==document.getElementById("lot")){
           s_sortie.options[0].innerHTML="��������";
          // s_issue.options[0].innerHTML="��������";
          }
       }
    
}

function initSelect(oElement) {
    while(oElement.options.length > 0) {
        oElement.remove(oElement.options.length-1);
    }
    var op=new Option("���ݼ�����...");        
    oElement.options.add(op);
    op.value="";
}

function saveissue(type,sid) {
    if(sid!=""){
        //ddz.innerHTML="���ݼ�����...";
        createXMLHttpRequest();
        var url = "bom_select?type="+encodeURI(type)+"&sid="+encodeURI(sid);
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);   
    }
}


function ClearAll(){
	document.getElementById("product_id").value="-------";
	document.getElementById("product_type").value="-------";
	document.getElementById("issue_num").value="-------";
	document.getElementById("lot").value="-------";
}


