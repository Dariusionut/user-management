"use strict";
const deleteUserHandler = function (formElement) {
    const fullName = formElement.getAttribute('data-fullName');
    return confirm(`Are you sure to permanently remove ${fullName}?`);
}
