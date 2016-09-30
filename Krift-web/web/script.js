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
                //alert("K = "+k+"  IGLENGTH = "+ig.length);
            }
            remove=false;
        }        
    }
    
    
    if(lastIg!=""){
        //alert("GOD");
        lastIg.onblur="";
        lastQt.onblur="";
        var cmp = document.createElement("DIV");
             cmp.innerHTML = "<h4 style='margin-top:10px'> Ingrediente "+(ig.length+1)+" </h4>                        <div>                        <input name = 'ingrediente' onblur = 'addIngrediente()' maxlength = '30' placeholder = 'Digite aqui o nome do ingrediente' type = 'text' style = 'width: 280px; margin-right: 10px;' />                        <input onblur = 'addIngrediente()' name = 'quantidade' maxlength = '40' placeholder = 'Digite aqui a quantidade' type = 'text' style = 'width: 290px;' />                        <span> NECESSÁRIO </span>                        </div>";
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
             cmp.innerHTML = "<h4 style='margin-top:10px'> Passo "+(ig.length+1)+" </h4>                        <div>                        <input name = 'passo' onblur = 'addPasso()' maxlength = '255' placeholder = 'Digite aqui o passo' type = 'text' />                   <span> NECESSÁRIO </span>                       ";
        box.appendChild(cmp);
    }
    
    
    
}