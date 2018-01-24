
$(function(){
	
    //add row
    $('#localTfrAddBtn').on("click", function(){
        
        var tblId = $(this).attr('data-target-table');
        var tblBody = $('#'+tblId).find('tbody');
        var tblBodyNumOfRow = tblBody.find('tr').length;
        var tdText = '<td><input type="text" name="${name}"></input></td>';
        var tr = $('<tr/>',{'class':'info'});
        
        tr.append(tdText.replace('${name}','transferInfoList['+tblBodyNumOfRow+'].bankName'));
        tr.append(tdText.replace('${name}','transferInfoList['+tblBodyNumOfRow+'].fromAccNo'));
        tr.append(tdText.replace('${name}','transferInfoList['+tblBodyNumOfRow+'].toAccNo'));

        tr.append('<td class="btnDelete"></td>');
        
        tblBody.append(tr);
    });
    
    
    //catering dynamic add/remove td
    //remove row
    $("#transferTbl").on("click", "td.btnDelete", function() {
        var rowIdx = $(this).parent().index();
        var tbody = $(this).parent().parent();

        $('tr',tbody).each(function(){
            if($(this).index() > rowIdx){
                $('td',$(this)).each(function(){
                    var tdHtml = $(this).html();
                    tdHtml = tdHtml.replace(/(.*?transferInfoList\[)(\d.*)(\].*)/, '$1'+ ($(this).parent().index()-1) + '$3');
                    $(this).html(tdHtml);
                });
            }
        });
        
        $('tr',tbody).eq(rowIdx).remove();
    });
    


});