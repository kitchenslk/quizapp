<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    <%@ include file="../includes/form_element_templates.jsp" %>
   
    
    <script type="text/javascript">
          var inputTemplate;
          var options=new Array;
          var inputElements=new Array;
          var calledFunction;
          $(function() {
            initializeForm();
            initializeFormOptions();
            initializeFormProperties();
            $( "#accordion" ).accordion();
           
            $( ".draggable" ).draggable({
                appendTo: "body",
                helper: "clone"
            });
            $( "#droppable" ).droppable({
              drop: function( event, ui ) {
                var draggable=ui.draggable;
                var id=$(draggable).attr('id');               
                var type=$('#' + id +' input[name="type"]').val();
                showElementDialog(type);                                                
              }
            });
            $( ".draggable" ).each(function(){
                $(this).click(function() {
                    var id=$(this).attr('id');               
                    var type=$('#' + id +' input[name="type"]').val();
                    showElementDialog(type); 
                });
            });
          });       
          
    </script>
    <div style="width: 100%; overflow: hidden;">
        <div class="left_div">
            <div id="accordion">
                <h3>Form Elements</h3>
                <div>
                  <div class="draggable" id="labels">
                        <input type="hidden" name="type" value="label"/>
                        <img src="img/new_label.png" /> Label
                  </div>
                  <div class="draggable" id="textBox">
                        <input type="hidden" name="type" value="text"/>
                        <img src="img/new_text.png" /> Text Box
                  </div>
                  <div class="draggable" id="password">
                        <input type="hidden" name="type" value="password"/>
                        <img src="img/new_password.png" /> Password Field
                  </div>
                  <div class="draggable" id="select">
                        <input type="hidden" name="type" value="select"/>
                        <img src="img/new_select.png" /> Select Box
                  </div>
                  <div class="draggable" id="radio">
                        <input type="hidden" name="type" value="radio"/>
                        <img src="img/new_radio.png" /> Radio Buttons
                  </div>
                  <div class="draggable" id="checkbox">
                        <input type="hidden" name="type" value="checkbox"/>
                        <img src="img/new_checkbox.png" /> Checkboxes
                  </div>
                  <div class="draggable" id="textarea">
                        <input type="hidden" name="type" value="textarea"/>
                        <img src="img/new_textarea.png" /> Textarea
                  </div>
                  <div class="draggable" id="file">
                        <input type="hidden" name="type" value="file"/>
                        <img src="img/new_file.png" /> File
                  </div>
                  <div class="draggable" id="datepicker">
                        <input type="hidden" name="type" value="datepicker"/>
                        <img src="img/new_date_picker.png" /> Datepicker
                  </div>
                  <div class="draggable" id="submit">
                        <input type="hidden" name="type" value="submit"/>
                        <img src="img/new_button.png" />Submit Button
                  </div>
                </div>
                <h3>Modify Form Elements</h3>
                <div>
                    <div id="edit">
                        <input type="hidden" name="type" value="edit"/>
                        <img src="img/property.png" />
                            <a href="javascript:editFormRow()">Edit</a>
                    </div>
                    <div id="delete">
                        <input type="hidden" name="type" value="delete"/>
                        <img src="img/delete.png" />
                           <a href="javascript:deleteFormRow()">Delete</a>
                    </div>
                </div>
                <h3>Form Actions</h3>
                <div>
                	<div id="edit_form">
                        <input type="hidden" name="type" value="edit_form"/>
                        <img src="img/property.png" />
                        <a href="javascript:editFormProperties()">Edit Form</a>
                    </div>  
                    <div id="save_form">
                        <input type="hidden" name="type" value="save_form"/>
                        <img src="img/save_xml.png" />
                        <a href="javascript:updateFormElements()">Update Form</a>
                    </div>                    
                </div>
            </div>
        </div>
        <div class="middle_div">
            <div id="droppable" style="width:100%;height:650px">
                <ul id="form_element_append">                     
                     <c:forEach items="${form.formFields}" var="formField">
                     	<c:choose>
                     		<c:when test="${formField.type=='label'}">
                     			<li class="form_row" id="${formField.uniqueId}" onclick="selectRow('${formField.uniqueId}')">
						            <div class="seperatorClass"></div>
						            <div class="inputClass">
						                <div class="labelVal" style="font-size: 15px;">${formField.label}</div>
						                <input type="hidden" size="${formField.size}" name="${formField.name}" id="${formField.id}" value="${formField.label}" class="inputField" />
						            </div>
						            <div class="cursor">                
						                <input type="hidden" id="inputType" name="inputType" value="${formField.type}" />
						                <input type="hidden" id="inputFieldPk" name="inputFieldPk" value="${formField.formFieldId}" />
						            </div>
						        </li>
                     		</c:when>
                     		<c:when test="${formField.type=='text'}">
                     			<li class="form_row" id="${formField.uniqueId}" onclick="selectRow('${formField.uniqueId}')">
						            <label class="labelClass">${formField.label}</label>
						            <div class="seperatorClass">:</div>
						            <div class="inputClass">
						                <input type="${formField.type}" size="${formField.size}" name="${formField.name}" id="${formField.id}" class="inputField" />
						            </div>
						            <div class="cursor">                
						                <input type="hidden" id="inputType" name="inputType" value="${formField.type}" /> 
						                <input type="hidden" id="inputFieldPk" name="inputFieldPk" value="${formField.formFieldId}" />               
						            </div>
						        </li>
                     		</c:when>
                     		<c:when test="${formField.type=='password'}">
                     			<li class="form_row" id="${formField.uniqueId}" onclick="selectRow('${formField.uniqueId}')">
						            <label class="labelClass">${formField.label}</label>
						            <div class="seperatorClass">:</div>
						            <div class="inputClass">
						                <input type="${formField.type}" size="${formField.size}" name="${formField.name}" id="${formField.id}" class="inputField" />
						            </div>
						            <div class="cursor">                
						                <input type="hidden" id="inputType" name="inputType" value="${formField.type}" /> 
						                <input type="hidden" id="inputFieldPk" name="inputFieldPk" value="${formField.formFieldId}" />               
						            </div>
						        </li>
                     		</c:when>
                     		<c:when test="${formField.type=='select'}">
                     			<li class="form_row" id="${formField.uniqueId}" onclick="selectRow('${formField.uniqueId}')">
						            <label class="labelClass">${formField.label}</label>
						            <div class="seperatorClass">:</div>
						            <div class="inputClass">
						                <select name="${formField.name}" id="${formField.id}" class="inputField">
						                    <c:forEach var="formFieldOption" items="${formField.options}">
						                        <option id="${formFieldOption.id}" value="${formFieldOption.value}" alt="${formFieldOption.formFieldOptionId}">${formFieldOption.display}</option>
						                    </c:forEach>
						                </select>
						            </div>
						            <div class="cursor">                
						                <input type="hidden" id="inputType" name="inputType" value="${formField.type}" />
						                <input type="hidden" id="inputFieldPk" name="inputFieldPk" value="${formField.formFieldId}" />
						            </div>
						        </li>
                     		</c:when>
                     		<c:when test="${formField.type=='radio'}">
                     			<li class="form_row" id="${formField.uniqueId}" onclick="selectRow('${formField.uniqueId}')">
						            <label class="labelClass">${formField.label}</label>
						            <div class="seperatorClass">:</div>
						            <div class="inputClass">
						                <c:forEach var="formFieldOption" items="${formField.options}">
						                    <input style="${formFieldOption.display}" type="radio" name="${formField.name}" id="${formFieldOption.id}" value="${formFieldOption.value}" class="inputField" alt="${formFieldOption.formFieldOptionId}" />
						                    <label class="inputDisplay">${formFieldOption.display}</label>
						               </c:forEach>
						            </div>
						            <div class="cursor">                
						                <input type="hidden" id="inputType" name="inputType" value="${formField.type}" />
						                <input type="hidden" id="inputFieldPk" name="inputFieldPk" value="${formField.formFieldId}" />
						            </div>
						        </li>
                     		</c:when>
                     		<c:when test="${formField.type=='checkbox'}">
                     			 <li class="form_row" id="${formField.uniqueId}" onclick="selectRow('${formField.uniqueId}')">
						            <label class="labelClass">${formField.label}</label>
						            <div class="seperatorClass">:</div>
						            <div class="inputClass">
						                <table style="width:100%">
						                    <c:forEach var="formFieldOption" items="${formField.options}">
						                        <tr>
						                            <td>
						                                <input style="${formFieldOption.display}" type="checkbox" name="${formField.name}" id="${formFieldOption.id}" value="${formFieldOption.value}" class="inputField" alt="${formFieldOption.formFieldOptionId}"/>
						                                <label class="inputDisplay">${formFieldOption.display}</label>
						                            </td>
						                        </tr>
						                    </c:forEach>
						                </table>
						            </div>
						            <div class="cursor">               
						                <input type="hidden" id="inputType" name="inputType" value="${formField.type}" />
						                <input type="hidden" id="inputFieldPk" name="inputFieldPk" value="${formField.formFieldId}" />
						            </div>
						        </li>
                     		</c:when>
                     		<c:when test="${formField.type=='textarea'}">
                  				<li class="form_row" id="${formField.uniqueId}" onclick="selectRow('${formField.uniqueId}')">
						            <label class="labelClass">${formField.label}</label>
						            <div class="seperatorClass">:</div>
						            <div class="inputClass">
						                <textarea id="${formField.id}" name="${formField.name}" class="inputField"></textarea>                            
						            </div>
						            <div class="cursor">                
						                <input type="hidden" id="inputType" name="inputType" value="${formField.type}" />
						                <input type="hidden" id="inputFieldPk" name="inputFieldPk" value="${formField.formFieldId}" />
						            </div>
						        </li>   		
                     		</c:when>
                     		<c:when test="${formField.type=='file'}">
                     			<li class="form_row" id="${formField.uniqueId}" onclick="selectRow('${formField.uniqueId}')">
						            <label class="labelClass">${formField.label}</label>
						            <div class="seperatorClass">:</div>
						            <div class="inputClass">
						                <input type="${formField.type}" size="${formField.size}" name="${formField.name}" id="${formField.id}" class="inputField" />
						            </div>
						            <div class="cursor">                
						                <input type="hidden" id="inputType" name="inputType" value="${formField.type}" /> 
						                <input type="hidden" id="inputFieldPk" name="inputFieldPk" value="${formField.formFieldId}" />               
						            </div>
						        </li>
                     		</c:when>
                     		<c:when test="${formField.type=='datepicker'}">
                     			 <li class="form_row" id="${formField.uniqueId}" onclick="selectRow('${formField.uniqueId}')">
						            <label class="labelClass">${formField.label}</label>
						            <div class="seperatorClass">:</div>
						            <div class="inputClass">
						                <input type="text" size="10" name="${formField.name}" id="${formField.id}" class="inputField" />
						                <script type="text/javascript">
						                    $('#${formField.id}').datepicker({ dateFormat: '${formField.format}'});
						                </script>
						            </div>
						            <div class="cursor">                
						                <input type="hidden" id="inputType" name="inputType" value="${formField.type}" />
						                <input type="hidden" id="format" name="format" value="${formField.format}" />
						                <input type="hidden" id="inputFieldPk" name="inputFieldPk" value="${formField.formFieldId}" />
						            </div>
						        </li>  
                     		</c:when>
                     		<c:when test="${formField.type=='submit'}">
              					<li class="form_row" id="${formField.uniqueId}" onclick="selectRow('${formField.uniqueId}')">
						            <label class="labelClass">&nbsp;</label>
						            <div class="seperatorClass">&nbsp;</div>
						            <div class="inputClass">
						                <input type="${formField.type}" size="${formField.size}" name="${formField.name}" id="${formField.id}" value="${formField.label}" class="inputField" />
						            </div>
						            <div class="cursor">                
						                <input type="hidden" id="inputType" name="inputType" value="${formField.type}" />
						                <input type="hidden" id="inputFieldPk" name="inputFieldPk" value="${formField.formFieldId}" />
						            </div>
						        </li>       		
                     		</c:when>
   						</c:choose>
                     </c:forEach>               
                </ul>
                <input type="hidden" id="formId" name="formId" value="${form.formId}" />
                <input type="hidden" id="formName" name="formName" value="${form.formName}" />
                <input type="hidden" id="formTitle" name="formTitle" value="${form.formTitle}" />
            </div>            
        </div>
    </div>
    
    
    <!-- The dialog box -->
    <div id="dialog-form" title="Add a form field" style="display:none">
        <p class="validateTips">All form fields are required.</p>       
        <form>
            <fieldset>
              <table>                
                <tr>
                    <td><label for="id">Id</label></td>
                    <td>:</td>
                    <td><input type="text" name="id" id="id" value="" class="text ui-widget-content ui-corner-all" /></td>
                </tr>
                <tr>
                    <td><label for="name">Name</label></td>
                    <td>:</td>
                    <td><input type="text" name="name" id="name" value="" class="text ui-widget-content ui-corner-all" /></td>
                </tr>
                <tr>
                    <td><label for="label">Label</label></td>
                    <td>:</td>
                    <td><input type="text" name="label" id="label" value="" class="text ui-widget-content ui-corner-all" /></td>
                </tr>
                <tr id="size_field">
                    <td><label for="size">Size</label></td>
                    <td>:</td>
                    <td><input type="text" name="size" id="size" value="" class="text ui-widget-content ui-corner-all" /></td>
                </tr>
                <tr style="display:none" id="option_row">
                    <td>
                        <label>
                            <a href="javascript:addSelectOption('add')">Add Options</a>
                        </label>
                    </td>
                    <td>:</td>
                    <td>                        
                        <div id="form_option_appending_div">
                            
                        </div>
                    </td>
                </tr>                
                <tr style="display:none" id="format_row">
                    <td><label for="format">Format</label></td>
                    <td>:</td>
                    <td>                       
                        <select id="format" name="format" class="text ui-widget-content ui-corner-all">
                            <option value="mm/dd/yy">Default - mm/dd/yy</option>
                            <option value="yy-mm-dd">ISO 8601 - yy-mm-dd</option>
                            <option value="d M, y">Short - d M, y</option>
                            <option value="d MM, y">Medium - d MM, y</option>
                            <option value="DD, d MM, yy">Full - DD, d MM, yy</option>
                        </select>
                    </td>
                </tr>
              </table>
              <input type="hidden" name="type" id="type" value="" />
            </fieldset>
        </form>
    </div>
    
    <!-- The dialog box -->   
    <div id="dialog-form-properties" title="Form properties" style="display:none">
        <p class="validateTips">All form fields are required.</p>        
        <form>
            <fieldset>
              <table>                
                <tr>
                    <td><label for="formId_dialog">Form Id</label></td>
                    <td>:</td>
                    <td><input type="text" name="formId_dialog" id="formId_dialog" value="" class="text ui-widget-content ui-corner-all" /></td>
                </tr>
                <tr>
                    <td><label for="formName_dialog">From Name</label></td>
                    <td>:</td>
                    <td><input type="text" name="formName_dialog" id="formName_dialog" value="" class="text ui-widget-content ui-corner-all" /></td>
                </tr>
                <tr>
                    <td><label for="formTitle_dialog">From Title</label></td>
                    <td>:</td>
                    <td><input type="text" name="formTitle_dialog" id="formTitle_dialog" value="" class="text ui-widget-content ui-corner-all" /></td>
                </tr>
              </table>
            </fieldset>
        </form>
    </div>
    
    <!-- The dialog box -->
    <div id="dialog-form-options" title="Add an option" style="display:none">
       <p class="validateTips">All form fields are required.</p>  
        <form>
            <fieldset>
              <table>                
<!--                 <tr> -->
<!--                     <td><label for="option_id">Id</label></td> -->
<!--                     <td>:</td> -->
<!--                     <td><input type="text" name="option_id" id="option_id" value="" class="text ui-widget-content ui-corner-all" /></td> -->
<!--                 </tr> -->
                <tr>
                    <td><label for="option_value">Value</label></td>
                    <td>:</td>
                    <td><input type="text" name="option_value" id="option_value" value="" class="text ui-widget-content ui-corner-all" /></td>
                </tr>
                <tr>
                    <td><label for="option_display_value">Display Value</label></td>
                    <td>:</td>
                    <td><input type="text" name="option_display_value" id="option_display_value" value="" class="text ui-widget-content ui-corner-all" /></td>
                </tr>
              </table>
            </fieldset>
        </form>
    </div>
    
    
    <div id="form_dialog_edit_append" style="display:none;">
        
        
    </div>

    
    
