import { Widgets } from './dbConnectors';

const resolvers = {
    getProduct: async ({ id }) => {
        try {
            const product = await Widgets.findById(id);
            return product;
        } catch (error) {
            throw new Error(error);
        }
    },
    createProduct: ({ input }) => {
        // let id = require('crypto').randomBytes(10).toString('hex');
        // productDatabase[id] = input;
        // return new Product(id, input);
    }
}

export default resolvers;
