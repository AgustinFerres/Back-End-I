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
            odontologoSkeleton.forEach(item => item.setAttribute('id','oculto'))
            crearArticle(data);

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
            pacienteSkeleton.forEach(item => item.setAttribute('id','oculto'))
            crearArticle(data);

        })
        .catch(err => console.log(err))
    }

    const getTurnos =  (e) => {

        const crearArticle = (arr) => {


            arr.forEach(async turno => {

                const odontologo = await fetch(ENDPOINT + `odontologos/buscar?id=${turno.idOdontologo}`).then(res => res.json()).then(data => data).catch(err => console.log(err))

                const paciente = await fetch(ENDPOINT + `pacientes/buscar?id=${turno.idPaciente}`).then(res => res.json()).then(data => data).catch(err => console.log(err))

                turnoSection.innerHTML += `
                    <article >
                        <div>
                            <h2> ${odontologo.nombre} ${odontologo.apellido} | nº: ${odontologo.matricula}</h2>
                            <p> paciente: ${paciente.nombre} ${paciente.apellido}</p>
                            <p> ${turno.fecha}<p/>
                        </div>
                    </article>
                `
            });
        }
        fetch(ENDPOINT + `turnos`)
        .then(res => res.json())
        .then(data => {
            turnoSkeleton.forEach(item => item.setAttribute('id','oculto'))
            crearArticle(data);

        })
        .catch(err => console.log(err))


    }

    getOdontologos();
    getPacientes();
    getTurnos();
})