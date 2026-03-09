export const ValidateLocation = (location) => {
    const locationRegex = /^[A-Za-z\s,]{2,50}$/;
    return locationRegex.test(location);
}