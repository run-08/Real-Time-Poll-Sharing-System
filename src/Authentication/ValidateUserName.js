export const ValidateUserName = (name) => {
   return name !== undefined &&
      name !== null &&
      name.trim().length != 0;  
}