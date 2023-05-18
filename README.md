# Personal Blogger backend

The idea is to have a storage of Posts (model) which will persist in memory (or file) which could be decoded by a Frontend component.

## h2 db

Currently configured to run in memory, which is a small problem; since data will only persist as long as the JVM is running, but still it works for the moment.

## Dependencies

- Lombok
- H2
- JPA

## Future

I want to keep expanding the model, for exapmle it could be customized with a Messages (model) to store contact requests in a webpage.
