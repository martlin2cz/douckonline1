

/* global PF */

function hideOnSuccess(xhr, status, args, widgetVar) {
    
    if (args.notValid || args.validationFailed) {
	console.warn("Validation failed");
	return;
    }
    
    PF(widgetVar).hide();  
    updateWholePage();
}

