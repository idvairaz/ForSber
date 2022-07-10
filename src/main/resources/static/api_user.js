var url = "/user/api_user"

fetch(url)
    .then((response) => {
        let res = response.json();
        return res;
    })
    .then((user) => {
        console.log(user);
        let roles = ""
        user.roles.forEach((role)=>{roles = roles + role.name + ' '});
        let tbody = document.getElementById('table_user');
        let tr = document.createElement('tr');
        tr.innerHTML = '<td>' + user.id + '</td>' +
            '<td>' + user.username + '</td>' +
            '<td>' + roles + '</td>' +
            '<td>' + user.name + '</td>'+
            '<td>' + user.lastName + '</td>' +
            '<td>' + user.age + '</td>';
        tbody.appendChild(tr);
    })

