/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function userChoice(type) {
    var subject = document.getElementById('subject');
    var tutorname = document.getElementById('firstname');
    var tutorlastname = document.getElementById('lastname');
    var status = document.getElementById('status');
    if (type === 'subject') {
        subject.style.display = 'block';
        tutorname.style.display = 'none';
        tutorlastname.style.display='none';
        status.style.display = 'none';
    } else if (type === 'firstname') {
        tutorname.style.display = 'block';
        subject.style.display = 'none';
        tutorlastname.style.display='none';
        status.style.display = 'none';
    } else if (type === 'lastname') {
        tutorlastname.style.display = 'block';
        tutorname.style.display = 'none';
        subject.style.display = 'none';
        status.style.display = 'none';
    } else {
        status.style.display = 'block';
        tutorname.style.display = 'none';
        subject.style.display = 'none';
        tutorlastname.style.display = 'none';
    }
}
function userType(type) {
    var specialty = document.getElementById('specialty');
    var specialtylabel = document.getElementById('specialtylabel');
    if (type === 'tutor') {
        specialty.style.display = 'block';
        specialtylabel.style.display = 'block';
    } else {
        specialty.style.display = 'none';
        specialtylabel.style.display = 'none';
    }
}