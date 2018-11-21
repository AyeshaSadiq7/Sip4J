package graphconstruction;

import graphstructure.E_MVertice;




public class LabeledEdge{
		private E_MVertice v1;
        private E_MVertice v2;
        private String label;

        public LabeledEdge(E_MVertice source, E_MVertice target, String label) {
            this.v1 = source;
            this.v2 = target;
            this.label = label;
            this.setSource(source);
            this.setTarget(target);
            
        }

        public E_MVertice getSource() {
            return v1;
        }

        public E_MVertice getTarget() {
            return v2;
        }

        public String toString() {
            return label;
        }

		public String getLabel() {
			return label;
		}

		public void setLabel(String lb) {
			this.label = lb;
		}

		
		public void setSource(E_MVertice v1) {
			this.v1 = v1;
		}

		
		public void setTarget(E_MVertice v2) {
			this.v2 = v2;
		}
		
    }
