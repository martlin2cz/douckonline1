

/* global PF */
//var TIMEOUT_BEFORE_UPDATE = 1 * 1000;

function hideOnSuccess(xhr, status, args, widgetVar) {

    if (args.notValid || args.validationFailed) {
	console.warn("Validation failed");
	return;
    }

    PF(widgetVar).hide();
    updateWholePage();

    //setTimeout(TIMEOUT_BEFORE_UPDATE, updateWholePage());
}

