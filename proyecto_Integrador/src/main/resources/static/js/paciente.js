window.addEventListener('load', () => {


    const pacienteSection = document.querySelector('.pacientes');
    const pacienteSkeleton = document.querySelectorAll('.pacientes .skeleton');
    const pacienteBuscadoSection = document.querySelector('.pop-up');

    const buscarForm = document.querySelector('.buscar form');
    const agregarForm = document.querySelector('.agregar form');
    const actualizarForm = document.querySelector('.actualizar form');
    const borrarForm = document.querySelector('.borrar form');




    const ENDPOINT = 'http://localhost:8080/'

    /* -------------------------------------------------------------------------- */
    /*                                 SELECT ALL                                 */
    /* -------------------------------------------------------------------------- */
    const getPacientes = (e) => {
        const crearArticle = (arr) => {
            arr.forEach(paciente => {
                pacienteSection.innerHTML += `
                    <article >
                        <img src="../assets/user-profile-300x300.jpg" alt="">
                        <div>
                            <h2>${paciente.nombre} ${paciente.apellido}</h2>
                            <p>${paciente.dni}</p>
                            <p>${paciente.email}</p>
                        </div>
                    </article>
                `
            });
        }
        fetch(ENDPOINT + `pacientes`)
        .then(res => res.json())
        .then(data => {
            if (data.length > 0){
                pacienteSkeleton.forEach(item => item.setAttribute('id','oculto'))
                crearArticle(data);
            }

        })
        .catch(err => console.log(err))


    }


    /* -------------------------------------------------------------------------- */
    /*                                SELECT BY EMAIL                                */
    /* -------------------------------------------------------------------------- */
    const getPaciente = (e) => {

        e.preventDefault()
        const email = e.target[0].value;


        const crearArticle = (paciente) => {

            pacienteBuscadoSection.innerHTML = `
                <article class='paciente'>
                    <div>
                        <img src="../assets/user-profile-300x300.jpg" alt="" class="profile">
                        <div>
                            <h2>${paciente.nombre} ${paciente.apellido}</h2>
                            <p>DNI: ${paciente.dni}</p>
                            <p>Email: ${paciente.email}</p>
                            <div>
                                <p>Calle: ${paciente.domicilio.calle}<p/>
                                <p>Numero: ${paciente.domicilio.numero}<p/>
                                <p>Localidad: ${paciente.domicilio.localidad}<p/>
                                <p>Provincia: ${paciente.domicilio.provincia}<p/>
                            </div>
                        </div>
                    </div>
                    <button id='closePopUp'>Close</button>
                </article>
            `
            const crossBuscado = document.querySelector('#closePopUp')
            crossBuscado.addEventListener('click', cerrarPopUp)
        }

        fetch(ENDPOINT + `pacientes/admin/buscar/mail?email=${email}`)
        .then(res => res.status === 403 ? location.replace("/pages/notAllowed.html") : res.json())
        .then(data => {
            pacienteBuscadoSection.removeAttribute('id')
            crearArticle(data);

        })
        .catch(err => console.log(err))
    }


    buscarForm.addEventListener('submit', getPaciente)


    const cerrarPopUp = () => {
        pacienteBuscadoSection.setAttribute('id', 'oculto')
    }


    /* -------------------------------------------------------------------------- */
    /*                             AGREGAR paciente                             */
    /* -------------------------------------------------------------------------- */
    const postPaciente = (e) => {

        e.preventDefault()

        const nombre = e.target[0].value
        const apellido = e.target[1].value
        const dni = e.target[2].value
        const email = e.target[3].value
        const domicilio = {
            calle: e.target[5].value,
            numero: e.target[6].value,
            localidad: e.target[7].value,
            provincia: e.target[8].value
        }

        const dateNow = new Date();
        const fecha_ingreso = dateNow
        fetch(ENDPOINT + 'pacientes/admin',{
            method: "POST",
            body: JSON.stringify({
                nombre,
                apellido,
                dni,
                fecha_ingreso,
                domicilio,
                email
            }),
            headers: {
                "content-type": 'application/json'
            }
        }

        ).then(res => res.status === 403 ? location.replace("/pages/notAllowed.html") : location.reload())
    }

    agregarForm.addEventListener('submit', postPaciente)



    /* -------------------------------------------------------------------------- */
    /*                            ACTUALIZAR paciente                           */
    /* -------------------------------------------------------------------------- */
    const putPaciente = (e) => {

        e.preventDefault();

        const id = e.target[0].value
        const nombre = e.target[1].value
        const apellido = e.target[2].value
        const matricula = e.target[3].value

        fetch(ENDPOINT + 'pacientes/admin',{
            method: "POST",
            body: JSON.stringify({
                id,
                matricula,
                nombre,
                apellido
            }),
            headers: {
                "content-type": 'application/json'
            }
        }).then(res => res.status === 403 ? location.replace("/pages/notAllowed.html") : location.reload())
    }
    actualizarForm.addEventListener('submit', putPaciente);

    /* -------------------------------------------------------------------------- */
            /*                                BORRAR PACIENTE                                */
    /* -------------------------------------------------------------------------- */

            const deletePaciente = (e) => {
                e.preventDefault()

                const id = e.target[0].value;

                fetch(ENDPOINT + `pacientes/admin/borrar?id=${id}`, {method: "DELETE"}).then(res => res.status === 403 ? location.replace("/pages/notAllowed.html") : location.reload())

            }

    borrarForm.addEventListener('submit', deletePaciente);
    getPacientes();
})