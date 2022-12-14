window.addEventListener('load', () => {


    const turnoSection = document.querySelector('.turnos');
    const turnoSkeleton = document.querySelectorAll('.turnos .skeleton');
    const turnoBuscadoSection = document.querySelector('.pop-up');

    const buscarForm = document.querySelector('.buscar form');
    const agregarForm = document.querySelector('.agregar form');
    const actualizarForm = document.querySelector('.actualizar form');
    const borrarForm = document.querySelector('.borrar form');



    const ENDPOINT = 'http://localhost:8080/'

    /* -------------------------------------------------------------------------- */
              /*                                 SELECT ALL                                 */
    /* -------------------------------------------------------------------------- */
    const getTurnos =  (e) => {

        const crearArticle = (arr) => {


            arr.forEach(async turno => {

                const odontologo = await fetch(ENDPOINT + `odontologos/admin/buscar?id=${turno.idOdontologo}`).then(res => res.json()).then(data => data).catch(err => console.log(err));

                const paciente = await fetch(ENDPOINT + `pacientes/admin/buscar?id=${turno.idPaciente}`).then(res => res.json()).then(data => data).catch(err => console.log(err));

                turnoSection.innerHTML += `
                <article >
                    <div>
                        <h2> ${odontologo.nombre} ${odontologo.apellido} | nº: ${odontologo.matricula}</h2>
                        <p> paciente: ${paciente.nombre} ${paciente.apellido}</p>
                        <p> ${turno.fecha}<p/>
                        <p>Id: ${turno.id}</p>
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
        .catch(err => console.log(err));


    }


    /* -------------------------------------------------------------------------- */
              /*                                SELECT BY ID                                */
    /* -------------------------------------------------------------------------- */
    const getTurno = (e) => {

        e.preventDefault()
        const id = e.target[0].value;

        const crearArticle = async (turno) => {

            const odontologo = await fetch(ENDPOINT + `odontologos/admin/buscar?id=${turno.idOdontologo}`).then(res => res.json()).then(data => data).catch(err => console.log(err));

            const paciente = await fetch(ENDPOINT + `pacientes/admin/buscar?id=${turno.idPaciente}`).then(res => res.json()).then(data => data).catch(err => console.log(err));

            turnoBuscadoSection.innerHTML = `
                <article class='turno'>
                    <div>
                        <div>
                            <h2>Odontologo</h2>
                            <h3>${odontologo.nombre} ${odontologo.apellido}</h3>
                            <p>Id: ${odontologo.id}</p>
                            <p>Nº: ${odontologo.matricula}</p>
                        </div>
                        <hr>
                        <div>
                            <h2>Paciente</h2>
                            <h3>${paciente.nombre} ${paciente.apellido}</h3>
                            <p>Id: ${paciente.id}</p>
                            <p>Email: ${paciente.email}</p>
                        </div>
                        <p class="fecha">Fecha: ${turno.fecha}<p/>
                    </div>
                    <button id='closePopUp'>Close</button>
                </article>
            `
            const crossBuscado = document.querySelector('#closePopUp')
            crossBuscado.addEventListener('click', cerrarPopUp)
        }

        fetch(ENDPOINT + `turnos/admin/buscar?id=${id}`)
        .then(res => res.status === 403 ? location.replace("/pages/notAllowed.html") : res.json())
        .then(data => {
            turnoBuscadoSection.removeAttribute('id')
            crearArticle(data);

        })
        .catch(err => console.log(err))
    }


    buscarForm.addEventListener('submit', getTurno)


    const cerrarPopUp = () => {
        turnoBuscadoSection.setAttribute('id', 'oculto')
    }


    /* -------------------------------------------------------------------------- */
                /*                             AGREGAR turno                             */
    /* -------------------------------------------------------------------------- */
    const postTurno = (e) => {

        e.preventDefault()

        const idOdontologo = e.target[0].value;
        const idPaciente = e.target[1].value;
        const fecha = e.target[2].value;

        fetch(ENDPOINT + 'turnos/admin',{
            method: "POST",
            body: JSON.stringify({
                idOdontologo,
                idPaciente,
                fecha,
            }),
            headers: {
                "content-type": 'application/json'
            }
        }

        ).then(res => res.status === 403 ? location.replace("/pages/notAllowed.html") : location.reload())
    }

    agregarForm.addEventListener('submit', postTurno)



    /* -------------------------------------------------------------------------- */
                /*                            ACTUALIZAR turno                           */
    /* -------------------------------------------------------------------------- */
    const putTurno = (e) => {

        e.preventDefault();

        const id = e.target[0].value;
        const idOdontologo = e.target[1].value;
        const idPaciente = e.target[2].value;
        const fecha = e.target[3].value;

        fetch(ENDPOINT + 'turnos/admin/actualizar',{
            method: "POST",
            body: JSON.stringify({
                id,
                idOdontologo,
                idPaciente,
                fecha
            }),
            headers: {
                "content-type": 'application/json'
            }
        }).then(res => res.status === 403 ? location.replace("/pages/notAllowed.html") : location.reload())
    }
    actualizarForm.addEventListener('submit', putTurno);

    /* -------------------------------------------------------------------------- */
            /*                                BORRAR TURNO                                */
    /* -------------------------------------------------------------------------- */

        const deleteTurno = (e) => {
            e.preventDefault()

            const id = e.target[0].value;

            fetch(ENDPOINT + `turnos/admin/borrar?id=${id}`, {method: "DELETE"}).then(res => res.status === 403 ? location.replace("/pages/notAllowed.html") : location.reload())

        }

    borrarForm.addEventListener('submit', deleteTurno);
    getTurnos();
})