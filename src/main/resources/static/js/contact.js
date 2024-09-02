console.log("Contact.js");

const baseUrl = "http://localhost:8080";

// Get the modal element
const viewContactModal = document.getElementById("view_contact_modal");

// Check if the modal element is found
if (!viewContactModal) {
    console.error("Modal with ID 'view_contact_modal' not found.");
}

// Options for the modal
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses: 'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// Initialize the modal
let contactModal;
document.addEventListener('DOMContentLoaded', () => {
    if (viewContactModal) {
        contactModal = new Modal(viewContactModal, options);
    }
});

// Function to open the modal
function openContactModal() {
    if (contactModal) {
        contactModal.show();
    } else {
        console.error("Cannot open modal: contactModal is not initialized.");
    }
}

async function loadContactData(id){
        console.log(id);
        try{
            const data = await (await fetch(`${baseUrl}/api/contacts/${id}`) ).json();
        console.log(data);
        document.querySelector("#contact_image").src = data.picture;
        document.querySelector("#contact_name").innerHTML = data.name
        document.querySelector("#contact_email").innerHTML = data.email
        document.querySelector("#contact_phoneNumber").innerHTML = data.phoneNumber
        document.querySelector("#contact_address").innerHTML = data.address
        document.querySelector("#contact_description").innerHTML = data.description
        const contactFavorite = document.querySelector("#contact_favorite");
        if(data.favorite){
           contactFavorite.innerHTML = "<i class= 'fas fa-star text-yellow-400'></i> <i class= 'fas fa-star text-yellow-400'></i> <i class= 'fas fa-star text-yellow-400'></i> <i class= 'fas fa-star text-yellow-400'></i> "
        }else{
            contactFavorite.innerHTML = "Not Favorite Contact"
        }
       
        const contactInstagramLink = document.querySelector("#contact_instagramLink");
        if (contactInstagramLink) {
            if (data.instagramLink) {
                contactInstagramLink.href = data.instagramLink;
                contactInstagramLink.innerHTML = data.instagramLink;
            } else {
                contactInstagramLink.innerHTML = "No Instagram Link";
                contactInstagramLink.href = "#";
            }
        }

        const contactLinkedInLink = document.querySelector("#contact_linkedInLink");
        if (contactLinkedInLink) {
            if (data.linkedInLink) {
                contactLinkedInLink.href = data.linkedInLink;
                contactLinkedInLink.innerHTML = data.linkedInLink;
            } else {
                contactLinkedInLink.innerHTML = "No LinkedIn Link";
                contactLinkedInLink.href = "#";
            }
        }
        
        openContactModal();
        }catch(error){
            console.log("Error:", error)
        }
       

    }

//delete contact
async function deleteContact(id){

    Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!"
      }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire({
                title: "Deleted!",
                text: "Your file has been deleted.",
                icon: "success"
              });
              setTimeout(()=> {
                const url = `${baseUrl}/user/contacts/delete/` +id;
                window.location.replace(url);
              }, 2000); //2 second delay
            

        }
      });

}    