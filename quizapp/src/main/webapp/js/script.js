function form_details(formId,formName,formTitle,formFields){
	this.formId=formId;
	this.formName=formName;
	this.formTitle=formTitle;
	this.formFields=formFields;
}

function input_element(id,name,label,size,type,options,format,value,uniqueId,formFieldId){
    this.id=id;
    this.name=name;
    this.label=label;
    this.size=size;
    this.type=type;
    this.options=options;
    this.format=format;
    this.value=value;
    this.uniqueId=uniqueId;
    this.formFieldId=formFieldId;
}

function input_element_option(value, display, id,formFieldOptionId) {
    this.value=value;
    this.display=display;
    this.id=id;
    this.formFieldOptionId=formFieldOptionId;
}



function initializeForm() {		
    $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 'auto',
      width: 500,
      modal: true,
      buttons: {
        "Add Field": function() {
          var bValid = true;         
          if ( bValid ) {
            var id=$('#id_add');
            var name=$('#name_add');
            var label=$('#label_add');            
            var size=$('#size_add');
            var type=$('#type_add').val();
            var format=$('#format_add').val();
            var value=$('#value_add').val();
            var uniqueId=$("#form_element_append li").size();           
            uniqueId='row_'  + (uniqueId + 1);
            inputTemplate=new input_element(id.val(),name.val(),label.val(),size.val(),type,options,format,value,uniqueId,'0');            
            console.log(inputTemplate);
            appendFormField(inputTemplate);           
            $("#form_element_append").sortable({
                stop: function( event, ui ) {
                    console.log(ui.item);
                }   
            });
            $( this ).dialog( "close" );
          }
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      },
      close: function() {
        clearDialogFields();
        clearOptionDialogFields();
      }
    });
}

function clearDialogFields() {
    $('#dialog-form input').each(function() {
        $(this).val('');        
    })
    $('#option_row').hide();
    $('#size_field').show();
    $('#format_row').hide();
    options=[];
    $('#form_option_appending_div').html('');
}

function appendFormField(inputTemplate) {   
    switch (inputTemplate.type) {
        case 'label':        	
             $("#formRowLabelTemplate").tmpl(inputTemplate).appendTo("#form_element_append");
            break;
        case 'text':
            $("#formRowTemplate").tmpl(inputTemplate).appendTo("#form_element_append");
            break;
        case 'password':
            $("#formRowTemplate").tmpl(inputTemplate).appendTo("#form_element_append");
            break;
        case 'select':
            $("#formRowSelectTemplate").tmpl(inputTemplate).appendTo("#form_element_append");
            break;
        case 'radio':
            $("#formRowRadioTemplate").tmpl(inputTemplate).appendTo("#form_element_append");
            break;
        case 'checkbox':
            $("#formRowCheckboxTemplate").tmpl(inputTemplate).appendTo("#form_element_append");
            break;
        case 'textarea':
            $("#formRowTextAreaTemplate").tmpl(inputTemplate).appendTo("#form_element_append");
            break;
        case 'file':
            $("#formRowTemplate").tmpl(inputTemplate).appendTo("#form_element_append");
            break;
        case 'datepicker':
            $("#formRowDatepickerTemplate").tmpl(inputTemplate).appendTo("#form_element_append");
            break;
        case 'submit':
            $("#formRowButtonTemplate").tmpl(inputTemplate).appendTo("#form_element_append");
            break;
    }    
}

function addSelectOption(calledBy) {
    calledFunction=calledBy;
    $( "#dialog-form-options" ).dialog( "open" ); 
}

function initializeFormOptions() {
    $( "#dialog-form-options" ).dialog({
      autoOpen: false,
      height: 'auto',
      width: 400,
      modal: false,
      buttons: {
        "Add Option": function() {
          var bValid = true;         
          if ( bValid ) {
            var option_id=$('#option_id').val();
            var option_value=$('#option_value').val();
            var option_display_value=$('#option_display_value').val();           
            appendFormOptionField(option_id,option_value,option_display_value);
            $( this ).dialog( "close" );
          }
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      },
      close: function() {
        clearOptionDialogFields();
      }
    });
}

function clearOptionDialogFields() {
    $('#dialog-form-options input').each(function() {
        $(this).val('');        
    })    
}

function appendFormOptionField(option_id,option_value,option_display_value) {
    var option=new input_element_option(option_value, option_display_value, option_id);
    options.push(option);
    appendHtmlOption();    
}

function removeOption(id) {
    $('#option_' + id).remove();
    id=parseInt(id);    
    options.splice((id-1),1);
    appendHtmlOption();
}

function appendHtmlOption() {
    var html="";
    $.each(options, function(index,value){
        html=html + '<div id="option_' + index + '">' + value.display + ' <span><a href="javascript:removeOption(\'' + index + '\')">X</a></span></div>';
    });
    if (calledFunction=='add') {
      $('#form_option_appending_div').html(html);
    }else{
        $('#form_option_appending_div_edit').html(html);
    }
    
}

function selectRow(rowId) {
    $('.selectedRow').each(function (){
        $(this).removeClass('selectedRow');
    });
    $('#' + rowId).addClass('selectedRow');   
}

function editFormRow() {
    var selectedId=$('.selectedRow').attr('id');    
    var inputTemplate=getConstructedInputTemplate(selectedId);   
    showEditingDialog(inputTemplate);
}

function showEditingDialog(inputTemplate) {
    $( "#form_dialog_edit_append" ).html('');
    $("#formEditDialogTemplate").tmpl(inputTemplate).appendTo("#form_dialog_edit_append");
    initializeEditForm();
    var type=inputTemplate.type;
    if (type=='select' || type=='radio' || type=='checkbox') {
        $('#option_rowEdit').show();
        $('#size_fieldEdit').hide();
    }else if (type=='textarea') {
         $('#size_fieldEdit').hide();    
    }else if (type=='file') {
         $('#size_fieldEdit').hide();
    }else if (type=='datepicker') {
         $('#size_fieldEdit').hide();
         $('#format_rowEdit').show();
    }else if (type=='submit') {
         $('#size_fieldEdit').hide();
         $('#format_rowEdit').hide();
    }
    $( "#dialog-form-edit" ).dialog( "open" ); 
    
}

function deleteFormRow() {
     $('.selectedRow').remove();     
}

function getConstructedInputTemplate(selectedId){   
    var label=$('#' + selectedId  + ' label.labelClass').html();         
    var type=$('#' + selectedId  + ' div.cursor #inputType').val();  
    var formFieldId=$('#' + selectedId  + ' div.cursor #inputFieldPk').val();
    return constructInputElement(type,selectedId,label,formFieldId);
}

function constructInputElement(type,uniqueId,label,formFieldId) {   
    var id,name,size,format,value;    
    switch (type) {
        case 'label':
            var input=$('#' + uniqueId  + ' div.inputClass .inputField');
            id=$(input).attr('id');
            name=$(input).attr('name');
            size=$(input).attr('size');
            label=$(input).val();
            break;
        case 'text':
            var input=$('#' + uniqueId  + ' div.inputClass .inputField');
            id=$(input).attr('id');
            name=$(input).attr('name');
            size=$(input).attr('size');
            break;
        case 'password':
            var input=$('#' + uniqueId  + ' div.inputClass .inputField');
            id=$(input).attr('id');
            name=$(input).attr('name');
            size=$(input).attr('size');
            break;
        case 'select':
            var input=$('#' + uniqueId  + ' div.inputClass .inputField');            
            id=$(input).attr('id');
            name=$(input).attr('name');
            options=[];
            $("#"  + id + " option").each(function(index,val){
                var value=$(this).attr('value');
                var id=index;
                var display=$(this).text();
                var formFieldOptionId=$(this).attr('alt');
                var element=new input_element_option(value, display, id,formFieldOptionId);
                options.push(element);
            }); 
            break;
        case 'radio':
            options=[];
            $('#' + uniqueId  + ' div.inputClass .inputField').each(function(index,val) {
                name=$(this).attr('name');
                var value=$(this).attr('value');
                var ids=index;
                id=$(this).attr('id');
                var display=$(this).attr('style');
                var formFieldOptionId=$(this).attr('alt');
                var element=new input_element_option(value, display, ids,formFieldOptionId);
                options.push(element);       
            })  
            break;
        case 'checkbox':
            options=[];
            $('#' + uniqueId  + ' div.inputClass .inputField').each(function(index,val) {
                name=$(this).attr('name');
                var value=$(this).attr('value');
                var ids=index;
                id=$(this).attr('id');
                var display=$(this).attr('style');
                var formFieldOptionId=$(this).attr('alt');
                var element=new input_element_option(value, display, ids, formFieldOptionId);
                options.push(element);       
            }) 
            break;
        case 'textarea':
            var input=$('#' + uniqueId  + ' div.inputClass .inputField');
            id=$(input).attr('id');
            name=$(input).attr('name');
            break;
        case 'file':
            var input=$('#' + uniqueId  + ' div.inputClass .inputField');
            id=$(input).attr('id');
            name=$(input).attr('name');
            size=$(input).attr('size');
            break;
        case 'datepicker':           
            var input=$('#' + uniqueId  + ' div.inputClass .inputField');
            id=$(input).attr('id');
            name=$(input).attr('name');
            size=$(input).attr('size');
            format=$('#' + uniqueId  + ' div.cursor #format').val();            
            break;
        case 'submit':
            var input=$('#' + uniqueId  + ' div.inputClass .inputField');
            id=$(input).attr('id');
            name=$(input).attr('name');
            size=$(input).attr('size');
            label=$(input).attr('value');
            break;
    }
    var inputTemplate=new input_element(id,name,label,size,type,options,format,value,uniqueId,formFieldId);
    return inputTemplate;
}

function initializeEditForm() {
    $( "#dialog-form-edit" ).dialog({
      autoOpen: false,
      height: 'auto',
      width: 500,
      modal: true,
      buttons: {
        "Edit Field": function() {            
            var id=$('#idEdit');            
            var name=$('#nameEdit');            
            var label=$('#labelEdit');          
            var size=$('#sizeEdit');            
            var type=$('#typeEdit').val();            
            var format=$('#formatEdit').val();           
            var value=$('#valueEdit').val();           
            var uniqueId=$("#uniqueIdEdit").val();
            var formFieldId=$("#formFieldIdEdit").val();
            $('#' + uniqueId).remove();
            inputTemplate=new input_element(id.val(),name.val(),label.val(),size.val(),type,options,format,value,uniqueId,formFieldId);            
            appendFormField(inputTemplate);           
            $("#form_element_append").sortable({
                stop: function( event, ui ) {
                    console.log(ui.item);
                }   
            });
            $( this ).dialog( "close" );
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      },
      close: function() {
        clearEditDialogFields();
        clearOptionDialogFields();
        $( this).dialog( "destroy" );
      }
    });
}

function clearEditDialogFields() {
   $('#dialog-form-edit input').each(function() {
        $(this).val('');        
    })  
}

function showElementDialog(type) {
    if (!(type==null || type=='' || type=='undefined')) {
        $('#type_add').val(type);
        if (type=='select' || type=='radio' || type=='checkbox') {
            $('#option_row').show();
            $('#size_field').hide();
        }else if (type=='textarea') {
             $('#size_field').hide();                    
        }else if (type=='file') {
             $('#size_field').hide();
        }else if (type=='datepicker') {
             $('#size_field').hide();
             $('#format_row').show();
        }else if (type=='submit') {
             $('#size_field').hide();
             $('#format_row').hide();
        }
        $( "#dialog-form" ).dialog( "open" );
    }
}

function initializeFormProperties() {	
    $( "#dialog-form-properties" ).dialog({
      autoOpen: false,
      height: 'auto',
      width: 500,
      modal: true,
      buttons: {
        "Save": function() {
          var bValid = true;         
          if ( bValid ) {
            //var id=$('#formId_dialog').val();
            var name=$('#formName_dialog').val();
            var label=$('#formTitle_dialog').val();                      
            //$('#formId').val(id);   
            $('#formName').val(name);
            $('#formTitle').val(label);
            $("#form_element_append").sortable({
                stop: function( event, ui ) {
                    console.log(ui.item);
                }   
            });
            $( this ).dialog( "close" );
          }
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      },
      close: function() {
        
      }
    });
}

function editFormProperties(){
	$('#formId_dialog').val($('#formId').val());
    $('#formName_dialog').val($('#formName').val());
    $('#formTitle_dialog').val($('#formTitle').val());        

    $( "#dialog-form-properties" ).dialog( "open" ); 
}


function saveFormElements() {
    inputElements.length=0;
    $('.form_row').each(function() {
         var selectedId=$(this).attr('id');
         console.log('selectedId :' + selectedId);
         var label=$('#' + selectedId  + ' label.labelClass').html();         
         var type=$('#' + selectedId  + ' div.cursor #inputType').val();   
         var formFieldId=$('#' + selectedId  + ' div.cursor #inputFieldPk').val();
         var item= constructInputElement(type,selectedId,label,formFieldId);
         inputElements.push(item);
    });
    var form=new form_details($('#formId').val(),$('#formName').val(),$('#formTitle').val(),inputElements);
    
    $.get('saveForm.htm',{"formString":JSON.stringify(form)},function(data){
    	
			window.location='listAllForms.htm';
		
	});
}

function updateFormElements(){
	inputElements.length=0;
    $('.form_row').each(function() {
         var selectedId=$(this).attr('id');
         console.log('selectedId :' + selectedId);
         var label=$('#' + selectedId  + ' label.labelClass').html();         
         var type=$('#' + selectedId  + ' div.cursor #inputType').val();   
         var formFieldId=$('#' + selectedId  + ' div.cursor #inputFieldPk').val();
         var item= constructInputElement(type,selectedId,label,formFieldId);
         inputElements.push(item);
    });
    var form=new form_details($('#formId').val(),$('#formName').val(),$('#formTitle').val(),inputElements);
    
    $.get('updateForm.htm',{"formString":JSON.stringify(form)},function(data){
    	
    	//window.location='listAllForms.htm';
		
	});
	
}





