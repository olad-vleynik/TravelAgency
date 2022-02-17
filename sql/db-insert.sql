# adding access levels
INSERT INTO access_levels
VALUES (1, 'administrator');

INSERT INTO access_levels
VALUES (2, 'manager');

INSERT INTO access_levels
VALUES (3, 'client');


# password "qwerty"
INSERT INTO users
VALUES (DEFAULT, 'Владислав', 'Олейник', '+380968054573', 'olad.vleynik@gmail.com',
        'ZfTmZ7+tYnNTNX4LjQzazw==zVX2OP0UfOlGq+E3Xo9ArWW5tSPMuKehi9tOqouEs42T/ZoMYdAhDJqlJ3FGrHdli/FSYJ7JFso9BPO+LgSGvA==',
        '1997-04-11T10:00:09', 10000, DEFAULT, DEFAULT, DEFAULT, 1);


# adding transfer types
INSERT INTO transfer_types
VALUES (1, 'airplane');

INSERT INTO transfer_types
VALUES (2, 'bus');

INSERT INTO transfer_types
VALUES (3, 'cruise liner');

INSERT INTO transfer_types
VALUES (4, 'train');


# adding tour types
INSERT INTO tour_types
VALUES (1, 'relax');

INSERT INTO tour_types
VALUES (2, 'sightseeing');

INSERT INTO tour_types
VALUES (3, 'shopping');


# adding tour states
INSERT INTO tour_states
VALUES (1, 'available');

INSERT INTO tour_states
VALUES (2, 'ongoing');

INSERT INTO tour_states
VALUES (3, 'completed');

INSERT INTO tour_states
VALUES (4, 'canceled');


# adding order states
INSERT INTO order_states
VALUES (1, 'booked');

INSERT INTO order_states
VALUES (2, 'payed');

INSERT INTO order_states
VALUES (3, 'canceled');
