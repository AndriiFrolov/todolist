fetch("validationMessage.html")
  .then(response => {
    return response.text()
  })
  .then(data => {
    document.querySelector("#validation-block").innerHTML = data;
  });