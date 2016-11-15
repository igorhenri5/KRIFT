function addIngrediente() {
    var box = document.getElementById('igq');
    var ig = document.getElementsByName("ingrediente");
    var qt = document.getElementsByName("quantidade");    
    
    var lastIg = (ig.item(ig.length-1).value);
    var lastQt = (qt.item(qt.length-1).value);
    
    
    var remove = false;    
    var j = 0;
    
    for(i=0; i<ig.length-1 ;i++){   
        if(ig.item(i).value=="" || qt.item(i).value==""){  
            remove = true;
        }
        if(remove){
            j = ig.length;
            for(k=i;k<j-1;k++){
                box.removeChild(box.lastChild);
            }
            remove=false;
        }        
    }
    
    
    if(lastIg!="" && lastQt!=""){
        //alert("GOD");
        lastIg.onblur="";
        lastQt.onblur="";
        var cmp = document.createElement("DIV");
             cmp.innerHTML = "<h4 style='margin-top:10px'> Ingrediente "+(ig.length+1)+" </h4>                        <div>                        <input name = 'ingrediente' onkeyup = 'addIngrediente()' maxlength = '30' placeholder = 'Digite aqui o nome do ingrediente' type = 'text' style = 'width: 280px; margin-right: 10px;' />                        <input onkeyup = 'addIngrediente()' name = 'quantidade' maxlength = '40' placeholder = 'Digite aqui a quantidade' type = 'text' style = 'width: 290px;' />                        <span> NECESSÁRIO </span>                        </div>";
        box.appendChild(cmp);
    }
    
    
    
}

function addPasso() {
    var box = document.getElementById('pss');
    var ig = document.getElementsByName("passo");   
    
    var lastIg = (ig.item(ig.length-1).value);    
    
    var remove = false;    
    var j = 0;
    
    for(i=0; i<ig.length-1 ;i++){   
        if(ig.item(i).value==""){  
            remove = true;
        }
        if(remove){
            j = ig.length;
            for(k=i;k<j-1;k++){
                box.removeChild(box.lastChild);
            }
            remove=false;
        }        
    }
    
    
    if(lastIg!=""){
        lastIg.onblur="";
        var cmp = document.createElement("DIV");
             cmp.innerHTML = "<h4 style='margin-top:10px'> Passo "+(ig.length+1)+" </h4>                        <div>                        <input name = 'passo' onkeyup = 'addPasso()' maxlength = '255' placeholder = 'Digite aqui o passo' type = 'text' />                   <span> NECESSÁRIO </span>                       ";
        box.appendChild(cmp);
    }
    
    
    
}

function startRead() {  
  // obtain input element through DOM 
  
  var file = document.getElementById('fileupload').files[0];
  if(file){
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = loaded;
  }
}

function loaded(evt){
    document.getElementById('fileaux').value=evt.target.result;
    
	div = document.getElementById('form');
	div.style.backgroundImage = 'url('+evt.target.result+')';
}


/*
function addIngrediente() {
    var box = document.getElementById('igq');
    var ig = document.getElementsByClassName("ingrediente");
    var qt = document.getElementsByClassName("quantidade");    
    
    var lastIg = (ig.item(ig.length-1).value);
    var lastQt = (qt.item(qt.length-1).value);
    
    
    var remove = false;    
    var j = 0;
    
    for(i=0; i<ig.length-1 ;i++){   
        if(ig.item(i).value=="" || qt.item(i).value==""){  
            remove = true;
        }
        if(remove){
            j = ig.length;
            for(k=i;k<j-1;k++){
                box.removeChild(box.lastChild);
                //alert("K = "+k+"  IGLENGTH = "+ig.length);
            }
            remove=false;
        }        
    }
    
    
    if(lastIg!=""){
        lastIg.onblur="";
        lastQt.onblur="";
        var cmp = document.createElement("DIV");
             cmp.innerHTML = "<h4 style='margin-top:10px'> Ingrediente "+(ig.length+1)+" </h4>                        <div>                        <input class='ingrediente' name = 'ingrediente"+ig.length+"' onblur = 'addIngrediente()' maxlength = '30' placeholder = 'Digite aqui o nome do ingrediente' type = 'text' style = 'width: 280px; margin-right: 10px;' />                        <input onblur = 'addIngrediente()' class='quantidade' name = 'quantidade"+qt.length+"' maxlength = '40' placeholder = 'Digite aqui a quantidade' type = 'text' style = 'width: 290px;' />                        <span> NECESSÁRIO </span>                        </div>";
        box.appendChild(cmp);
    }
        
    
}

function addPasso() {
    var box = document.getElementById('pss');
    var ig = document.getElementsByClassName("passo");   
    
    var lastIg = (ig.item(ig.length-1).value);    
    
    var remove = false;    
    var j = 0;
    
    for(i=0; i<ig.length-1 ;i++){   
        if(ig.item(i).value==""){  
            remove = true;
        }
        if(remove){
            j = ig.length;
            for(k=i;k<j-1;k++){
                box.removeChild(box.lastChild);
            }
            remove=false;
        }        
    }
    
    
    if(lastIg!=""){
        lastIg.onblur="";
        var cmp = document.createElement("DIV");
             cmp.innerHTML = "<h4 style='margin-top:10px'> Passo "+(ig.length+1)+" </h4>                        <div>                        <input class='passo' name = 'passo"+ig.length+"' onblur = 'addPasso()' maxlength = '255' placeholder = 'Digite aqui o passo' type = 'text' />                   <span> OPCIONAL </span>                       ";
             
        box.appendChild(cmp);
    }
    
    
    
}





function startRead() {  
  // obtain input element through DOM 
  
  var file = document.getElementById('fileupload').files[0];
  if(file){
    readAsDataURL(file);
  }
}

function readAsDataURL(readFile) {
        
  var reader = new FileReader();
  
  reader.readAsDataURL(readFile);
  
  // Handle progress, success, and errors
  reader.onload = loaded;
}

function loaded(evt){
    document.getElementById('fileaux').value=evt.target.result;
    
	div = document.getElementById('form');
	div.style.backgroundImage = 'url('+evt.target.result+')';
}



function validaReceita(){
    var k = true;
    
    var ingredientes = document.getElementById("igq").getElementsByTagName("input");    
    var procedimentos = document.getElementById("pss").getElementsByTagName("input");
    
    var validI = new Array();
    var validP = new Array();
    
    var nome = document.getElementsByName("nome");
    alert("Nome da receita: "+nome[0].value);
    
         
    for(i=0; i<ingredientes.length; i++){
        if(ingredientes[i].value == null || ingredientes[i].value == ""){
            if(i>1){
                if(i<(ingredientes.length-2)){
                    k = false;
                }                
            }
        }else{
            validI.push(ingredientes[i].value);       
        }
    }
    
    for(i=0; i<procedimentos.length; i++){
        if(procedimentos[i].value == null || procedimentos[i].value == ""){
            if(i>1){
                if(i<(procedimentos.length-2)){
                    k = false;
                }                
            }
        }else{
            validP.push(procedimentos[i].value);       
        }
    }
    
    document.getElementById("qtdIG").value=validI.length;
    document.getElementById("qtdPS").value=validP.length;
    
    alert(validI);
    alert(validP);
    
    if(k){
        document.getElementById("form").submit();
    }else{
        alert(" corrija ");
    }
    
    
}
 */