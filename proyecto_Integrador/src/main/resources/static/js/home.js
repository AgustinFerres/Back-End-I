window.addEventListener('load', () => {

    const odontologoSection = document.querySelector('.odontologos')
    const odontologoSkeleton = document.querySelectorAll('.odontologos .skeleton')
    const pacienteSection = document.querySelector('.pacientes')
    const pacienteSkeleton = document.querySelectorAll('.pacientes .skeleton')
    const turnoSection = document.querySelector('.turnos')
    const turnoSkeleton = document.querySelectorAll('.turnos .skeleton')
    const ENDPOINT = 'http://localhost:8080/'


    const getOdontologos = () => {

        const crearArticle = (arr) => {
            arr.forEach(odontologo => {
                odontologoSection.innerHTML += `
                    <article >
                        <img src="assets/user-profile-300x300.jpg" alt="">
                        <div>
                            <h2>${odontologo.nombre} ${odontologo.apellido}</h2>
                            <p>${odontologo.matricula}</p>
                        </div>
                    </article>
                `
            });
        }
        fetch(ENDPOINT + 'odontologos')
        .then(res => res.json())
        .then(data => {
            if (data.length > 0){
                odontologoSkeleton.forEach(item => item.setAttribute('id','oculto'))
                crearArticle(data);
            }
        })
        .catch(err => console.log(err))


    }

    const getPacientes = () => {

        const crearArticle = (arr) => {
            arr.forEach(paciente => {
                pacienteSection.innerHTML += `
                    <article >
                        <img src="assets/user-profile-300x300.jpg" alt="">
                        <div>
                            <h2>${paciente.nombre} ${paciente.apellido}</h2>
                            <p>${paciente.dni}</p>
                            <p>${paciente.email}</p>
                        </div>
                    </article>
                `
            });
        }
        fetch(ENDPOINT + 'pacientes')
        .then(res => res.json())
        .then(data => {
            if (data.length > 0){
                pacienteSkeleton.forEach(item => item.setAttribute('id','oculto'))
                crearArticle(data);
            }
        })
        .catch(err => console.log(err))
    }

    const getTurnos =  (e) => {

        const crearArticle = (arr) => {


            arr.forEach( turno => {


                turnoSection.innerHTML += `
                    <article >
                        <div>
                            <h2> Id Odontologo ${turno.idOdontologo}</h2>
                            <p> Id Paciente: ${turno.idPaciente}</p>
                            <p> ${turno.fecha}<p/>
                        </div>
                    </article>
                `
            });
        }
        fetch(ENDPOINT + `turnos`)
        .then(res => res.json())
        .then(data => {
            if (data.length > 0){
                turnoSkeleton.forEach(item => item.setAttribute('id','oculto'))
                crearArticle(data);
            }
        })
        .catch(err => console.log(err))


    }

    getOdontologos();
    getPacientes();
    getTurnos();
})