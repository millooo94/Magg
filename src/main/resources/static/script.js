const nazioniUrl = "http://localhost:8084/nazioni";
const regioniUrl = "http://localhost:8084/regioni";
const provinceBaseUrl = "http://localhost:8084/province";
const comuniBaseUrl = "http://localhost:8084/comuni";
const capBaseUrl = "http://localhost:8084/cap";
const getAddressBaseUrl = "http://localhost:8084/indirizzo";
const postAddressUrl = "http://localhost:8084/indirizzo";
const putAddressUrl = "http://localhost:8084/indirizzo";


let nazioni = [];
let regioni = [];
let province = [];
let comuni = [];
let caps = [];

const indirizzoInput = document.getElementById("indirizzo-input");
const nazioneSelect = document.getElementById("nazione-select");

const addressInputs = {
  regione: document.getElementById("regione-input"),
  provincia: document.getElementById("provincia-input"),
  comune: document.getElementById("comune-input"),
  cap: document.getElementById("cap-input"),
};

const addressSelects = {
  regione: document.getElementById("regione-select"),
  provincia: document.getElementById("provincia-select"),
  comune: document.getElementById("comune-select"),
  cap: document.getElementById("cap-select"),
};

let indirizzoSedePrincipale = {
  id: null,
  idAnagrafica: 4,
  idDocumento: null,
  tipoIndirizzo: "se",
  denominazioneAlternativa: null,
  nazione: nazioneSelect.value,
  indirizzo: indirizzoInput.value,
  provincia: addressInputs.provincia.value,
  comune: addressInputs.comune.value,
  frazione: null,
  regione: addressInputs.regione.value,
  cap: addressInputs.cap.value,
  codSdi: null,
  telefono1: null,
  telefono2: null,
  cellulare: null,
  telefono3: null,
  fax: null,
  email: null,
  pec: null,
  sito: null,
  referente: null,
  recapitoReferente: null,
  notes: null
};



const populateAddressFields = async (place) => {

  Object.values(addressInputs).forEach((input) => (input.value = ""));

  let address = {
    nazione: null,
    regione: null,
    provincia: null,
    comune: null,
    cap: null,
  };

  // Usa un ciclo for...of per l'iterazione
  for (const item of place.address_components) {
    if (item.types.includes("country")) {
      address.nazione = item.long_name;
    }

    if (item.types.includes("administrative_area_level_1")) {
      address.regione = item.long_name.replace(/-/g, " ");
    }

    if (item.types.includes("administrative_area_level_2")) {
      address.provincia = item.long_name;
    }

    if (
      item.types.includes("administrative_area_level_3") ||
      item.types.includes("locality")
    ) {
      address.comune = item.long_name;
    }

    if (item.types.includes("postal_code")) {
      address.cap = item.long_name;
    }
  }

  if (address.nazione === "Italia") {
      for (const key in addressSelects) {
        addressSelects[key].style.display = "block";
        addressInputs[key].style.display = "none";
      }
  } else {
      for (const key in addressSelects) {
        addressSelects[key].style.display = "none";
        addressInputs[key].style.display = "block";
      }
  }

  nazioneSelect.value = address.nazione;

  addressInputs.regione.value = address.regione;
  addressInputs.provincia.value = address.provincia;
  addressInputs.comune.value = address.comune;
  addressInputs.cap.value = address.cap;


  if (address.nazione === "Italia") {
      let provinciaArray = address.provincia.split(" ");
      address.provincia = provinciaArray[provinciaArray.length - 1];

      regioni = await getRegioni();
      addressSelects.regione.value = address.regione;

      province = await getProvince(address.regione);
      addressSelects.provincia.value = address.provincia;

      comuni = await getComuni(address.provincia);
      addressSelects.comune.value = address.comune;

      caps = await getCap(address.comune);
      addressSelects.cap.value = address.cap;
  }

  address.indirizzo = indirizzoInput.value;

  Object.assign(indirizzoSedePrincipale, address);

  await submitAddress(indirizzoSedePrincipale);

  console.log("address => ", indirizzoSedePrincipale);
};





const submitAddress = async (address) => {

if (indirizzoSedePrincipale.id === null) {

      fetch(postAddressUrl, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(address),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
          return response;
        })
        .catch((error) => {
          console.error("Errore:", error);
        });
} else {
    fetch(`${putAddressUrl}/${indirizzoSedePrincipale.id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(address),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
          return response.json(); // Restituisce il body della risposta come JSON
        })
        .then((data) => {
          console.log("Successo:", data); // Puoi usare 'data' se la patch restituisce dati
        })
        .catch((error) => {
          console.error("Errore:", error);
        });
    }

};



// Inizializzazione dell'Autocomplete di Google Places
document.addEventListener("DOMContentLoaded", async function () {
  await getIndirizzoSedePrincipale();

  const autocomplete = new google.maps.places.Autocomplete(indirizzoInput, {
    types: ["geocode"],
  });

  autocomplete.addListener("place_changed", function () {
    const nearPlace = autocomplete.getPlace();
    populateAddressFields(nearPlace);
  });
});

const getIndirizzoSedePrincipale = async () => {
  const idAnagrafica = localStorage.getItem("idAnagrafica");
  try {
    const response = await fetch(`${getAddressBaseUrl}/${idAnagrafica}`);
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const data = await response.json();
    Object.assign(indirizzoSedePrincipale, data);
    await setAddressValues(indirizzoSedePrincipale);
    console.log("INDIRIZZO SEDE PRINCIPALE", indirizzoSedePrincipale);
  } catch (error) {
    console.error("There has been a problem with your fetch operation:", error);
  }
};

const setAddressValues = async (address) => {

  await getNazioni();

  if (address.nazione === "Italia") {
      await getRegioni();
      await getProvince(address.regione);
      await getComuni(address.provincia);
      await getCap(address.comune);
  }

  indirizzoInput.value = address.indirizzo;
  nazioneSelect.value = address.nazione;

  addressInputs.regione.value = address.regione;
  addressInputs.provincia.value = address.provincia;
  addressInputs.comune.value = address.comune;
  addressInputs.cap.value = address.cap;

  addressSelects.regione.value = address.regione;
  addressSelects.provincia.value = address.provincia;
  addressSelects.comune.value = address.comune;
  addressSelects.cap.value = address.cap;
}; // Chiusura della funzione setAddressValues

// Ottieni i paesi
const getNazioni = async () => {
  try {
    const response = await fetch(nazioniUrl);
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const data = await response.json();
    nazioni = data.map((item) => item.nazione).sort();

    nazioneSelect.innerHTML = "";
    nazioni.forEach((item) => {
      const optionToAdd = document.createElement("option");
      optionToAdd.value = item;
      optionToAdd.textContent = item;
      nazioneSelect.appendChild(optionToAdd);
    });
  } catch (error) {
    console.error("There has been a problem with your fetch operation:", error);
  }
};

// Ottieni le regioni
const getRegioni = async () => {
  try {
    const response = await fetch(regioniUrl);
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const data = await response.json();
    regioni = data.map((item) => {
      return item.nome;
    });

    addressSelects.regione.innerHTML = "";
    regioni.forEach((item) => {
      const optionToAdd = document.createElement("option");
      optionToAdd.value = item;
      optionToAdd.textContent = item;
      addressSelects.regione.appendChild(optionToAdd);
    });
  } catch (error) {
    console.error("There has been a problem with your fetch operation:", error);
  }
};

const getProvince = async (regione = "Nessuno") => {
  try {
    const response = await fetch(`${provinceBaseUrl}/${regione}`);
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const data = await response.json();
    console.log(data);
    province = data.map((item) => {
      return item.nomeCompleto;
    });

    addressSelects.provincia.innerHTML = "";
    province.forEach((item) => {
      const optionToAdd = document.createElement("option");
      optionToAdd.value = item;
      optionToAdd.textContent = item;
      addressSelects.provincia.appendChild(optionToAdd);
    });
  } catch (error) {
    console.error("There has been a problem with your fetch operation:", error);
  }
};

const getComuni = async (provincia = "Nessuno") => {
  try {
    const response = await fetch(`${comuniBaseUrl}/${provincia}`);
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const data = await response.json();
    console.log(data);
    comuni = data.map((item) => {
      return item.citta;
    });

    addressSelects.comune.innerHTML = "";
    comuni.forEach((item) => {
      const optionToAdd = document.createElement("option");
      optionToAdd.value = item;
      optionToAdd.textContent = item;
      addressSelects.comune.appendChild(optionToAdd);
    });
  } catch (error) {
    console.error("There has been a problem with your fetch operation:", error);
  }
};

const getCap = async (comune = "Nessuno") => {
  try {
    const response = await fetch(`${capBaseUrl}/${comune}`);
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const data = await response.json();
    caps = data.map((item) => {
      return item.codice;
    });

    addressSelects.cap.innerHTML = "";
    caps.forEach((item) => {
      const optionToAdd = document.createElement("option");
      optionToAdd.value = item;
      optionToAdd.textContent = item;
      addressSelects.cap.appendChild(optionToAdd);
    });
  } catch (error) {
    console.error("There has been a problem with your fetch operation:", error);
  }
};


