export const ValidateAge = (age) => {
    const ageRegex = /^(?:1[01][0-9]|120|[1-9]?[0-9])$/;
    return ageRegex.test(age);
}