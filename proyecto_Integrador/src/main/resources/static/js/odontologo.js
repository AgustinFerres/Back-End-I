window.addEventListener('load', () => {


    const odontologoSection = document.querySelector('.odontologos');
    const odontologoSkeleton = document.querySelectorAll('.odontologos .skeleton');
    const odontologoBuscadoSection = document.querySelector('.pop-up');

    const buscarForm = document.querySelector('.buscar form');
    const agregarForm = document.querySelector('.agregar form');
    const actualizarForm = document.querySelector('.actualizar form');
    const borrarForm = document.querySelector('.borrar form');




    const ENDPOINT = 'http://localhost:8080/'

    /* -------------------------------------------------------------------------- */
    /*                                 SELECT ALL                                 */
    /* -------------------------------------------------------------------------- */
    const getOdontologos = (e) => {
        const crearArticle = (arr) => {
            arr.forEach(odontologo => {
                odontologoSection.innerHTML += `
                    <article >
                        <img src="../assets/user-profile-300x300.jpg" alt="">
                        <div>
                            <h2>${odontologo.nombre} ${odontologo.apellido}</h2>
                            <p>${odontologo.matricula}</p>
                        </div>
                    </article>
                `
            });
        }
        fetch(ENDPOINT + `odontologos`)
        .then(res => res.json())
        .then(data => {
            if (data.length > 0){
                odontologoSkeleton.forEach(item => item.setAttribute('id','oculto'))
                crearArticle(data);
            }

        })
        .catch(err => console.log(err))


    }


    /* -------------------------------------------------------------------------- */
    /*                          SELECT BY NOMBRE COMPLETO                         */
    /* -------------------------------------------------------------------------- */
    const getOdontologo = (e) => {

        e.preventDefault()
        const nombreCompleto = e.target[0].value;
        const crearArticle = (odontologo) => {

            odontologoBuscadoSection.innerHTML = `
                <article>
                    <div>
                        <img src="../assets/user-profile-300x300.jpg" alt="" class="profile">
                        <div>
                            <h2>${odontologo.nombre} ${odontologo.apellido}</h2>
                            <p>${odontologo.matricula}</p>
                        </div>
                    </div>
                    <button id='closePopUp'>Close</button>
                </article>
            `
            const crossBuscado = document.querySelector('#closePopUp')
            crossBuscado.addEventListener('click', cerrarPopUp)
        }

        fetch(ENDPOINT + `odontologos/admin/buscarXnombre?nombreCompleto=${nombreCompleto}`)
        .then(res => res.status === 403 ? location.replace("/pages/notAllowed.html") : res.json())
        .then(data => {
            odontologoBuscadoSection.removeAttribute('id')
            crearArticle(data);

        })
        .catch(err => console.log(err))
    }


    buscarForm.addEventListener('submit', getOdontologo)


    const cerrarPopUp = () => {
        odontologoBuscadoSection.setAttribute('id', 'oculto')
    }


    /* -------------------------------------------------------------------------- */
    /*                             AGREGAR ODONTOLOGO                             */
    /* -------------------------------------------------------------------------- */
    const postOdontologo = (e) => {

        e.preventDefault()

        const nombre = e.target[0].value
        const apellido = e.target[1].value
        const matricula = e.target[2].value

        fetch(ENDPOINT + 'odontologos/admin',{
            method: "POST",
            body: JSON.stringify({
                matricula,
                nombre,
                apellido
            }),
            headers: {
                "content-type": 'application/json'
            }
        }

        ).then(res => res.status === 403 ? location.replace("/pages/notAllowed.html") : location.reload())
    }

    agregarForm.addEventListener('submit', postOdontologo)



    /* -------------------------------------------------------------------------- */
    /*                            ACTUALIZAR ODONTOLOGO                           */
    /* -------------------------------------------------------------------------- */
    const putOdontologo = (e) => {

        e.preventDefault();

        const id = e.target[0].value
        const nombre = e.target[1].value
        const apellido = e.target[2].value
        const matricula = e.target[3].value

        fetch(ENDPOINT + 'odontologos/admin',{
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
    actualizarForm.addEventListener('submit', putOdontologo);

    /* -------------------------------------------------------------------------- */
            /*                                BORRAR ODONTOLOGO                                */
    /* -------------------------------------------------------------------------- */

                const deleteOdontologo = (e) => {
                    e.preventDefault()

                    const id = e.target[0].value;

                    fetch(ENDPOINT + `odontologos/admin/borrar?id=${id}`, {method: "DELETE"}).then(res => res.status === 403 ? location.replace("/pages/notAllowed.html") : location.reload())

                }

        borrarForm.addEventListener('submit', deleteOdontologo);

    getOdontologos();
})