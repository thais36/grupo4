function makeDate(){
	obj = document.getElementById('dataNascimento');
	vl = obj.value;
	l = vl.toString().length;
	switch(l){
		case 2:
			obj.value = vl + "/";
		break;
		case 5:
			obj.value = vl +"/";
		break;
	}
			
	
}